package es.deusto.sd.eurostyletuning.external;

import java.util.List;
import java.util.Optional;

import es.deusto.sd.eurostyletuning.dto.PartDTO;

public interface IGateway {
	public Optional<List<PartDTO>> getParts();
	public Optional<List<PartDTO>> getPartsByBrandAndCategory(String brandName, String categoryName);
}