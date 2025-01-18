package es.deusto.sd.eurostyletuning.external;

import es.deusto.sd.eurostyletuning.dto.PartDTO;
import es.deusto.sd.eurostyletuning.dto.CategoryDTO;
import es.deusto.sd.eurostyletuning.dto.BrandDTO;

import java.util.List;
import java.util.Optional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ZILServiceGatewayImpl implements IGateway {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 9000;

    @Override
    public Optional<List<PartDTO>> getParts() {
        String response = sendRequest("getAllParts");
        return parseParts(response);
    }

    @Override
    public Optional<List<PartDTO>> getPartsByBrandAndCategory(String brand, String category) {
        String request = "getPartsByBrandAndCategory [" + brand + ", " + category + "]";
        String response = sendRequest(request);
        return parseParts(response);
    }

    private String sendRequest(String request) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            out.println(request);
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null && !line.isEmpty()) {
                response.append(line).append("\n");
            }

            return response.toString().trim();

        } catch (IOException e) {
            e.printStackTrace();
            return "Error connecting to server: " + e.getMessage();
        }
    }

    private Optional<List<PartDTO>> parseParts(String response) {
        List<PartDTO> parts = new ArrayList<>();

        // Dividir la respuesta en partes, cada una representada por "Part [ ... ]"
        String[] partStrings = response.split("Part \\[");
        for (String partString : partStrings) {
            if (partString.trim().isEmpty()) continue;  // Ignorar cadenas vacías

            // Limpiar el "Part [" al inicio y "]" al final
            partString = partString.replace("]", "").trim();

            // Dividir los atributos de la parte (por ejemplo, partID, description, price, etc.)
            String[] attributes = partString.split(", ");

            // Extraer los valores de cada atributo
            String partID = "";
            String description = "";
            int price = 0;
            String supplier = "";
            int categoryID = 0;
            String categoryName = "";
            int brandID = 0;
            String brandName = "";

            for (String attribute : attributes) {
                if (attribute.startsWith("partID=")) {
                    partID = attribute.substring("partID=".length()).trim();
                } else if (attribute.startsWith("description=")) {
                    description = attribute.substring("description=".length()).trim();
                } else if (attribute.startsWith("price=")) {
                    price = Integer.parseInt(attribute.substring("price=".length()).trim());
                } else if (attribute.startsWith("supplier=")) {
                    supplier = attribute.substring("supplier=".length()).trim();
                } else if (attribute.startsWith("categoryID=")) {
                    categoryID = Integer.parseInt(attribute.substring("categoryID=".length()).trim());
                } else if (attribute.startsWith("categoryName=")) {
                    categoryName = attribute.substring("categoryName=".length()).trim();
                } else if (attribute.startsWith("brandID=")) {
                    brandID = Integer.parseInt(attribute.substring("brandID=".length()).trim());
                } else if (attribute.startsWith("brandName=")) {
                    brandName = attribute.substring("brandName=".length()).trim();
                }
            }

            // Crear CategoryDTO y BrandDTO usando tanto el ID como el nombre
            CategoryDTO categoryDTO = new CategoryDTO(categoryID, categoryName); 
            BrandDTO brandDTO = new BrandDTO(brandID, brandName);

            // Crear un PartDTO a partir de los datos extraídos
            PartDTO part = new PartDTO(Integer.parseInt(partID), description, price, supplier, categoryDTO, brandDTO);
            parts.add(part);
        }

        return Optional.of(parts);
    }
}
