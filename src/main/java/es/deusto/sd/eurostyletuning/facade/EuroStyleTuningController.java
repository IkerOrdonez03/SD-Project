package es.deusto.sd.eurostyletuning.facade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.deusto.sd.eurostyletuning.dto.PurchaseRequestDTO;
import es.deusto.sd.eurostyletuning.service.EuroStyleTuningService;

@RestController
@RequestMapping("/eurostyletuning")
public class EuroStyleTuningController {

    private final EuroStyleTuningService tuningService;

    public EuroStyleTuningController(EuroStyleTuningService tuningService) {
        this.tuningService = tuningService;
    }
    
    @PostMapping("/purchase")
    public ResponseEntity<String> makePurchase(@RequestBody PurchaseRequestDTO purchaseRequest) {
        try {
            String responseMessage = tuningService.processPurchase(purchaseRequest);
            if (responseMessage.startsWith("Purchase confirmed")) {
                return new ResponseEntity<>(responseMessage, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

