package es.deusto.sd.eurostyletuning.service;

import java.util.List;

import es.deusto.sd.eurostyletuning.dto.BrandDTO;
import es.deusto.sd.eurostyletuning.dto.CategoryDTO;
import es.deusto.sd.eurostyletuning.dto.PartDTO;
import es.deusto.sd.eurostyletuning.dto.PurchaseRequestDTO;

public interface EuroStyleTuningService {
	String processPurchase(PurchaseRequestDTO purchaseRequest);
	List<CategoryDTO> collectCategories();
	List<BrandDTO> collectCarBrands();
	List<PartDTO> retrieveParts(int brandId, int categoryId);
	
	
}
