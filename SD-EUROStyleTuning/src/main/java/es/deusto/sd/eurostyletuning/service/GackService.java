package es.deusto.sd.eurostyletuning.service;

import java.util.List;

import es.deusto.sd.eurostyletuning.dto.PartDTO;

public interface GackService {
	public List<PartDTO> getAllParts();
	public List<PartDTO> getPartsByFilter(String brandName, String categoryName);
}
