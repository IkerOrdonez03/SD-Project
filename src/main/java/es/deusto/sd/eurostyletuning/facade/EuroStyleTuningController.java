package es.deusto.sd.eurostyletuning.facade;

import es.deusto.sd.eurostyletuning.assembler.EuroStyleTuningAssembler;
import es.deusto.sd.eurostyletuning.dto.BrandDTO;
import es.deusto.sd.eurostyletuning.dto.CategoryDTO;
import es.deusto.sd.eurostyletuning.dto.PartDTO;
import es.deusto.sd.eurostyletuning.dto.PurchaseRequestDTO;
import es.deusto.sd.eurostyletuning.dto.PurchaseResponseDTO;
import es.deusto.sd.eurostyletuning.entity.Brand;
import es.deusto.sd.eurostyletuning.entity.Category;
import es.deusto.sd.eurostyletuning.entity.Part;
import es.deusto.sd.eurostyletuning.entity.Purchase;
import es.deusto.sd.eurostyletuning.service.EuroStyleTuningService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eurostyle")
public class EuroStyleTuningController {

    private final EuroStyleTuningService euroStyleTuningService;
    private final EuroStyleTuningAssembler assembler;

    public EuroStyleTuningController(EuroStyleTuningService euroStyleTuningService, EuroStyleTuningAssembler assembler) {
        this.euroStyleTuningService = euroStyleTuningService;
        this.assembler = assembler;
    }

    // Obtener todas las marcas
    @GetMapping("/brands")
    public ResponseEntity<List<BrandDTO>> getAllBrands() {
        List<Brand> brands = euroStyleTuningService.getCarBrands();
        if (brands.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<BrandDTO> dtos = brands.stream().map(assembler::toBrandDTO).collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    // Obtener todas las categorías
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<Category> categories = euroStyleTuningService.getCategories();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<CategoryDTO> dtos = categories.stream().map(assembler::toCategoryDTO).collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    // Obtener partes por marca y categoría
    @GetMapping("/parts")
    public ResponseEntity<List<PartDTO>> getPartsByBrandAndCategory(
            @RequestParam("brandId") int brandId,
            @RequestParam("categoryId") int categoryId) {
        List<Part> parts = euroStyleTuningService.retrieveParts(brandId, categoryId);
        if (parts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<PartDTO> dtos = parts.stream().map(assembler::toPartDTO).collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    // Procesar una compra
    @PostMapping("/purchases")
    public ResponseEntity<PurchaseResponseDTO> processPurchase(@RequestBody PurchaseRequestDTO request) {
        Purchase purchase = new Purchase();
        purchase.setPartId(request.getPartId());
        purchase.setQuantity(request.getQuantity());
        purchase.setShippingAddress(request.getShippingAddress());

        String result = euroStyleTuningService.processPurchase(purchase);

        if ("Part not found".equals(result) || "Invalid quantity".equals(result)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        PurchaseResponseDTO response = new PurchaseResponseDTO(
                purchase.getId(),
                "Purchase processed successfully",
                purchase.getPurchaseDate()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
