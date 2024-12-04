package es.deusto.sd.gack.assembler;

import org.springframework.stereotype.Component;

import es.deusto.sd.gack.dto.BrandDTO;
import es.deusto.sd.gack.dto.CategoryDTO;
import es.deusto.sd.gack.dto.PartDTO;
import es.deusto.sd.gack.entity.Brand;
import es.deusto.sd.gack.entity.Category;
import es.deusto.sd.gack.entity.Part;

@Component
public class GackAssembler {
	public BrandDTO toBrandDTO(Brand brand) {
		return new BrandDTO(brand.getBrandID(), brand.getBrandName());
	}

	public CategoryDTO toCategoryDTO(Category category) {
		return new CategoryDTO(category.getCategoryID(), category.getCategoryName());
	}

	public PartDTO toPartDTO(Part part) {
		return new PartDTO(part.getPartId(), part.getDescription(), part.getPrice(), part.getCategory(),
				part.getBrand());
	}
}
