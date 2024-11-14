package es.deusto.sd.eurostyletuning.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import es.deusto.sd.eurostyletuning.entity.*;

@Service
public class EuroStyleTuningServiceImpl implements EuroStyleTuningService {
	
	private final List<Brand> brandRepository = new ArrayList<>();
    private final List<Category> categoryRepository = new ArrayList<>();

	@Override
	public String processPurchase(Purchase purchaseRequest) {
//        // Busca la pieza y verifica el stock
//        Part selectedPart = parts.stream()
//                .filter(part -> part.getId() == purchaseRequest.getPartId())
//                .findFirst()
//                .orElse(null);
//
//        if (selectedPart == null || selectedPart.getStock() < purchaseRequest.getQuantity()) {
//            return "Purchase failed: Part not available or insufficient stock";
//        }
//
//        // Genera una confirmación de compra
//        selectedPart.setStock(selectedPart.getStock() - purchaseRequest.getQuantity());
//        String purchaseId = UUID.randomUUID().toString();
//
//        return "Purchase confirmed with ID: " + purchaseId;
		return "TODO";
	}
	
	public List<Category> collectCategories() {
		
		List<Category> categories = new ArrayList<>();
				
		for (Category c: categoryRepository) {
			if(!(categories.contains(c))) {
				categories.add(c);
			}
		}
		return categories;
		
	}
	
	public List<Brand> collectCarBrands(){
		
		List<Brand> brands = new ArrayList<>();
		
		for (Brand b: brandRepository) {
			if(!(brands.contains(b))) {
				brands.add(b);
			}
		}
		return brands;
	}
	
	public List<Part> retrieveParts(int brandId, int categoryId){
//		TODO
//		List<Part> parts = new ArrayList<>();
//		
//		for (Brand brand : brandRepository) {
//			for (Category category : categoryRepository) {
//				if (brand.getBrandID() == brandId && category.getCategoryID() == categoryId) {
//					parts.add(new Part(1, "description", 0, "supplier", getCategoryById(categoryId), getBrandById(brandId)));
//				}
//			}
//		}
		
		return null;
	}

	@Override
	public void addBrand(Brand brand) {
		brandRepository.add(brand);
	}

	@Override
	public void addCategory(Category category) {
		categoryRepository.add(category);
	}

	@Override
	public Brand getBrandById(long id) {
		for (Brand brand : brandRepository) {
			if (brand.getBrandID() == id) {
				return brand;
			}
		}
		return null;
	}

	@Override
	public Category getCategoryById(long id) {
		for (Category category : categoryRepository) {
			if (category.getCategoryID() == id) {
				return category;
			}
		}
		return null;
	}
}