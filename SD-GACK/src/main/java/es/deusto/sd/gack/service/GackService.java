package es.deusto.sd.gack.service;

import java.util.List;

import es.deusto.sd.gack.entity.Part;

public interface GackService {
	List<Part> getAllParts();

	List<Part> getPartsByBrandAndCategory(String brandName, String categoryName);

	Part addPart(Part part);

	Part getPartById(int id);

	Part updatePart(int id, Part updatedPart);

	boolean deletePart(int id);
}
