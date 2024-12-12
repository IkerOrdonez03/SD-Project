package es.deusto.sd.eurostyletuning.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import es.deusto.sd.eurostyletuning.assembler.EuroStyleTuningAssembler;
import es.deusto.sd.eurostyletuning.dto.PartDTO;
import es.deusto.sd.eurostyletuning.entity.*;
import es.deusto.sd.eurostyletuning.external.EuroStyleGateway;
import es.deusto.sd.eurostyletuning.external.GackServiceGateway;
import es.deusto.sd.eurostyletuning.external.ZILServiceGateway;

@Service
public class EuroStyleTuningServiceImpl implements EuroStyleTuningService {
	
	@Autowired
    private EuroStyleTuningAssembler assembler;
	
	private static Map<String, Brand> brandRepository = new HashMap<>();
    private static Map<String, Category> categoryRepository = new HashMap<>();
    private static Map<String, Purchase> purchaseRepository = new HashMap<>();
    private static Map<String, Part> partRepository = new HashMap<>();

    public List<Category> getCategories() {
    	return categoryRepository.values().stream().toList();
		
	}
	
	public List<Brand> getCarBrands(){
		return brandRepository.values().stream().toList();
	}
	
	@Override
	public List<Purchase> getPurchases() {
		return purchaseRepository.values().stream().toList();
	}
	

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public Brand getBrandById(long id) {
		return brandRepository.get(id);
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public Category getCategoryById(long id) {
		return categoryRepository.get(id);
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Override
    public Purchase getPurchaseById(long id) {
        return purchaseRepository.get(id);
    }
	
	@Override
    public List<Part> retrieveParts(int brandId, int categoryId) {
        return partRepository.values().stream()
                             .filter(part -> part.getBrand().getBrandID() == brandId && part.getCategory().getCategoryID() == categoryId)
                             .collect(Collectors.toList());
    }
	
	@Override
    public String processPurchase(Purchase purchaseRequest) {
        Part part = partRepository.get(String.valueOf(purchaseRequest.getPartId()));
        if (part == null) {
            return "Part not found";
        }
        if (purchaseRequest.getQuantity() <= 0) {
            return "Invalid quantity";
        }
        
        String purchaseId = String.valueOf(purchaseRepository.size() + 1);
        purchaseRequest.setId(Long.parseLong(purchaseId));
        purchaseRequest.setPurchaseDate(LocalDateTime.now());
        purchaseRepository.put(purchaseId, purchaseRequest);
        return "Purchase processed successfully";
    }

	@Override
	public void addBrand(Brand brand) {
		if (brand != null) {
			brandRepository.put(String.valueOf(brand.getBrandID()), brand);
		}
	}
	
	@Override
	public void addCategory(Category category) {
		if (category != null) {
			categoryRepository.put(String.valueOf(category.getCategoryID()), category);
		}
	}

	@Override
    public void addPurchase(Purchase purchase) {
        String purchaseId = String.valueOf(purchaseRepository.size() + 1);
        purchase.setId(Long.parseLong(purchaseId));
        purchase.setPurchaseDate(LocalDateTime.now());
        purchaseRepository.put(purchaseId, purchase);
    }
	
	@Override
	public void addPart(Part part) {
	    if (part != null) {
	        partRepository.put(String.valueOf(part.getPartId()), part);
	    }
	}
	
	// Funciones (2 para ZIL y 2 para GACK)

    public String getAllPartsFromZIL() {
        ZILServiceGateway zilGateway = (ZILServiceGateway) EuroStyleGateway.createGateway(EuroStyleGateway.GatewayType.ZIL);
        return zilGateway.getAllParts();
    }

    public String getPartsByBrandAndCategoryFromZIL(String brand, String category) {
        ZILServiceGateway zilGateway = (ZILServiceGateway) EuroStyleGateway.createGateway(EuroStyleGateway.GatewayType.ZIL);
        return zilGateway.getPartsByBrandAndCategory(brand, category);
    }
    
    public List<Part> getAllPartsFromGack() {
        GackServiceGateway gackGateway = (GackServiceGateway) EuroStyleGateway.createGateway(EuroStyleGateway.GatewayType.GACK);
        Optional<List<PartDTO>> optionalPartDTOs = gackGateway.getAllPartsFromGack();

        return optionalPartDTOs
                .map(partDTOs -> partDTOs.stream()
                        .map(assembler::toPart)
                        .collect(Collectors.toList()))
                .orElse(List.of());
    }

    public List<Part> getPartsByBrandAndCategoryFromGack(String brandName, String categoryName) {
        GackServiceGateway gackGateway = (GackServiceGateway) EuroStyleGateway.createGateway(EuroStyleGateway.GatewayType.GACK);
        Optional<List<PartDTO>> optionalPartDTOs = gackGateway.getPartsFromGack(brandName, categoryName);

        return optionalPartDTOs
                .map(partDTOs -> partDTOs.stream()
                        .map(assembler::toPart)
                        .collect(Collectors.toList()))
                .orElse(List.of());
    }
}