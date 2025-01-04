package es.deusto.sd.zil.server.service;

import java.util.List;

import es.deusto.sd.zil.server.entity.Part;

public interface ZILService {
	List<Part> getAllParts();

	List<Part> getPartsByBrandAndCategory(String brandName, String categoryName);

	Part addPart(Part part);

	Part getPartById(int id);

	Part updatePart(int id, Part updatedPart);

	boolean deletePart(int id);
}