package es.deusto.sd.eurostyletuning.external;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ZILServiceGatewayImpl implements ZILServiceGateway {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 9000;

    @Override
    public String getAllParts() {
        return sendRequest("getAllParts");
    }

    @Override
    public String getPartsByBrandAndCategory(String brand, String category) {
        String request = "getPartsByBrandAndCategory [" + brand + ", " + category + "]";
        return sendRequest(request);
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
}