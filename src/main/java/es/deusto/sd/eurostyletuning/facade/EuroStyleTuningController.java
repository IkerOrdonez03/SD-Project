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
    
    @GetMapping("/purchases")
    public ResponseEntity<List<PurchaseResponseDTO>> getPurchases() {
        List<Purchase> purchases = euroStyleTuningService.getPurchases();
        if (purchases.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<PurchaseResponseDTO> dtos = purchases.stream().map(assembler::toPurchaseResponseDTO).collect(Collectors.toList());
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
    
    @GetMapping("/brands{id}")
    public ResponseEntity <BrandDTO> getBrandById(
    		@PathVariable int brandId) {
    	
        Brand brand = euroStyleTuningService.getBrandById(brandId);
        if (brand == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        BrandDTO bDTO = assembler.toBrandDTO(brand);
        return new ResponseEntity<>(bDTO, HttpStatus.OK);
    }
    
    @GetMapping("/category")
    public ResponseEntity <CategoryDTO> getCategoryById(
    		@PathVariable int categoryId) {
    	
    	Category category = euroStyleTuningService.getCategoryById(categoryId);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        CategoryDTO dto = assembler.toCategoryDTO(category);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    
    @GetMapping("/purchases")
    public ResponseEntity <PurchaseResponseDTO> getPurchaseById(
    		@PathVariable int purchaseId) {
    	Purchase p = euroStyleTuningService.getPurchaseById(purchaseId);
    	if (p == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    	PurchaseResponseDTO dto = assembler.toPurchaseResponseDTO(p);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    	
    }
    
    @PostMapping("/brand")
    public ResponseEntity <Void> addBrand(@RequestBody BrandDTO brandDTO) {
        Brand b = assembler.toBrand(brandDTO);
    
        euroStyleTuningService.addBrand(b);
        
        if(b == null) {
        	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(HttpStatus.OK);

    }
    
    @PostMapping("/category")
    public ResponseEntity <Void> addCategory(@RequestBody CategoryDTO dto) {
    	Category c = assembler.toCategory(dto);
    
        euroStyleTuningService.addCategory(c);
        
        if(c == null) {
        	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(HttpStatus.OK);

    }
    
    @PostMapping("/part")
    public ResponseEntity <Void> addPart(@RequestBody PartDTO dto) {
    	Part p = assembler.toPart(dto);
    
        euroStyleTuningService.addPart(p);
        
        if(p == null) {
        	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(HttpStatus.OK);

    }
    
    
}
