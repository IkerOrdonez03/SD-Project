package es.deusto.sd.zil.server.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import es.deusto.sd.zil.server.entity.Part;

public class ZILServiceImpl implements ZILService {
	private static Map<Integer, Part> partRepository = new HashMap<>();

	public List<Part> getAllParts() {
		return new ArrayList<>(partRepository.values());
	}

	public List<Part> getPartsByBrandAndCategory(String brandName, String categoryName) {
		return partRepository.values().stream()
				.filter(part -> part.getBrand().getBrandName().equalsIgnoreCase(brandName)
						&& part.getCategory().getCategoryName().equalsIgnoreCase(categoryName))
				.collect(Collectors.toList());
	}

	public Part addPart(Part part) {
		partRepository.put(part.getPartId(), part);
		return part;
	}

	public Part getPartById(int id) {
		return partRepository.get(id);
	}

	public Part updatePart(int id, Part updatedPart) {
		if (partRepository.containsKey(id)) {
			updatedPart.setPartId(id);
			partRepository.put(id, updatedPart);
			return updatedPart;
		}
		return null;
	}

	public boolean deletePart(int id) {
		return partRepository.remove(id) != null;
	}
}
