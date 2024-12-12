package es.deusto.sd.eurostyletuning.external;

public interface ZILServiceGateway {
    String getAllParts();
    String getPartsByBrandAndCategory(String brand, String category);
}