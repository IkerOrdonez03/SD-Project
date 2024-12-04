package es.deusto.sd.gack.service;

import es.deusto.sd.gack.entity.Part;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

@Service
public class GackServiceImpl implements GackService {
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
