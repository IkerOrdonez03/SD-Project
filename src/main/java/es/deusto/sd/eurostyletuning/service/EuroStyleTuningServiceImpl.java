package es.deusto.sd.eurostyletuning.service;

import org.springframework.stereotype.Service;

import es.deusto.sd.eurostyletuning.dto.PurchaseRequestDTO;

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
}