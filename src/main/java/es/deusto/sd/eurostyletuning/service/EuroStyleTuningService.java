package es.deusto.sd.eurostyletuning.service;

import java.util.List;

import es.deusto.sd.eurostyletuning.entity.Brand;
import es.deusto.sd.eurostyletuning.entity.Category;
import es.deusto.sd.eurostyletuning.entity.Part;
import es.deusto.sd.eurostyletuning.entity.Purchase;

public interface EuroStyleTuningService {
	String processPurchase(Purchase purchaseRequest);
	List<Category> collectCategories();
	List<Brand> collectCarBrands();
	List<Part> retrieveParts(int brandId, int categoryId);
	void addBrand(Brand brand);
	void addCategory(Category category);
	Brand getBrandById(long id);
	Category getCategoryById(long id);
	
}
