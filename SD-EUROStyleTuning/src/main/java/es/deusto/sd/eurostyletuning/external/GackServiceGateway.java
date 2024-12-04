package es.deusto.sd.eurostyletuning.external;

import java.util.List;
import java.util.Optional;

import es.deusto.sd.eurostyletuning.dto.PartDTO;

public interface GackServiceGateway {
	public Optional<List<PartDTO>> getPartsFromGack(String brandName, String categoryName);
	public Optional<List<PartDTO>> getAllPartsFromGack();
}
