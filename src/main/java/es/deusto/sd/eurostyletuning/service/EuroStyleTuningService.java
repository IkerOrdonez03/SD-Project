package es.deusto.sd.eurostyletuning.service;

import java.util.List;

import es.deusto.sd.eurostyletuning.entity.Brand;
import es.deusto.sd.eurostyletuning.entity.Category;
import es.deusto.sd.eurostyletuning.entity.Part;
import es.deusto.sd.eurostyletuning.entity.Purchase;

public interface EuroStyleTuningService {
	List<Category> getCategories();
	List<Brand> getCarBrands();
	List<Purchase> getPurchases();
	
	Brand getBrandById(long id);
	Category getCategoryById(long id);
	Purchase getPurchaseById(long id); 
	
	List<Part> retrieveParts(int brandId, int categoryId);
	String processPurchase(Purchase purchaseRequest);
	
	void addBrand(Brand brand);
	void addCategory(Category category);
	void addPurchase(Purchase purchase);
	void addPart(Part part);
	
}
