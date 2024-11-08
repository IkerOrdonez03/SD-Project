package es.deusto.sd.eurostyletuning.service;

import java.util.List;

import org.springframework.stereotype.Service;

import es.deusto.sd.eurostyletuning.dto.PurchaseRequestDTO;
import es.deusto.sd.eurostyletuning.dto.*;
@Service
public class EuroStyleTuningServiceImpl implements EuroStyleTuningService {

	@Override
	public String processPurchase(PurchaseRequestDTO purchaseRequest) {
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
//        // Reduce el stock y genera una confirmación de compra
//        selectedPart.setStock(selectedPart.getStock() - purchaseRequest.getQuantity());
//        String purchaseId = UUID.randomUUID().toString();
//
//        return "Purchase confirmed with ID: " + purchaseId;
		return "TODO";
	}
	
	public List<CategoryDTO> collectCategories() {
		return null;
		
	}
	
	public List<BrandDTO> collectCarBrands(){
		return null;
	}
	
	public List<PartDTO> retrieveParts(int brandId, int categoryId){
		return null;
	}
}