package es.deusto.sd.eurostyletuning.assembler;

import es.deusto.sd.eurostyletuning.dto.BrandDTO;
import es.deusto.sd.eurostyletuning.dto.CategoryDTO;
import es.deusto.sd.eurostyletuning.dto.PartDTO;
import es.deusto.sd.eurostyletuning.entity.Brand;
import es.deusto.sd.eurostyletuning.entity.Category;
import es.deusto.sd.eurostyletuning.entity.Part;
import org.springframework.stereotype.Component;

@Component
public class EuroStyleTuningAssembler {

	// Convertir clases a DTOs
	public BrandDTO toBrandDTO(Brand brand) {
	    return new BrandDTO(brand.getBrandID(), brand.getBrandName());
	}

	public CategoryDTO toCategoryDTO(Category category) {
	    return new CategoryDTO(category.getCategoryID(), category.getCategoryName());
	}

	public PartDTO toPartDTO(Part part) {
	    return new PartDTO(
	            part.getPartId(),
	            part.getDescription(),
	            part.getPrice(),
	            part.getSupplier(),
	            new CategoryDTO(part.getCategory().getCategoryID(), part.getCategory().getCategoryName()),
	            new BrandDTO(part.getBrand().getBrandID(), part.getBrand().getBrandName())
	    );
	}

	// Convertir DTOs a clases
	public Brand toBrand(BrandDTO brandDTO) {
	    return new Brand(brandDTO.getBrandID(), brandDTO.getBrandName());
	}

	public Category toCategory(CategoryDTO categoryDTO) {
	    return new Category(categoryDTO.getCategoryID(), categoryDTO.getCategoryName());
	}

	public Part toPart(PartDTO partDTO) {
	    return new Part(
	            partDTO.getPartId(),
	            partDTO.getDescription(),
	            partDTO.getPrice(),
	            partDTO.getSupplier(),
	            new Category(partDTO.getCategory().getCategoryID(), partDTO.getCategory().getCategoryName()),
	            new Brand(partDTO.getBrand().getBrandID(), partDTO.getBrand().getBrandName())
	    );
	}

}