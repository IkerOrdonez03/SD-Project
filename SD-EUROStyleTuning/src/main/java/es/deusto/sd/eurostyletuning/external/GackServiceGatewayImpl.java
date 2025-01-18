package es.deusto.sd.eurostyletuning.external;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.deusto.sd.eurostyletuning.dto.PartDTO;

@Component
public class GackServiceGatewayImpl implements IGateway {

	private final String GACK_API_BASE_URL = "http://localhost:8081/gack/parts";

	private final HttpClient httpClient;
	private final ObjectMapper objectMapper;

	public GackServiceGatewayImpl() {
		this.httpClient = HttpClient.newHttpClient();
		this.objectMapper = new ObjectMapper();
	}

	@Override
	public Optional<List<PartDTO>> getPartsByBrandAndCategory(String brandName, String categoryName) {
	    try {
	        // Codificar los parámetros
	        String encodedBrandName = URLEncoder.encode(brandName, StandardCharsets.UTF_8.toString());
	        String encodedCategoryName = URLEncoder.encode(categoryName, StandardCharsets.UTF_8.toString());

	        // Construir la URL con los parámetros codificados
	        String url = GACK_API_BASE_URL + "/filter?brandName=" + encodedBrandName + "&categoryName=" + encodedCategoryName;

	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(url))
	                .GET()
	                .build();

	        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

	        if (response.statusCode() == 200) {
	            List<PartDTO> parts = objectMapper.readValue(response.body(), new TypeReference<List<PartDTO>>() {});
	            return Optional.of(parts);
	        } else {
	            return Optional.empty();
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return Optional.empty();
	    }
	}

	@Override
	public Optional<List<PartDTO>> getParts() {
		try {
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(GACK_API_BASE_URL)).GET().build();

			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() == 200) {
				List<PartDTO> parts = objectMapper.readValue(response.body(), new TypeReference<List<PartDTO>>() {});
				return Optional.of(parts);
			} else {
				return Optional.empty();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return Optional.empty();
		}
	}
}
