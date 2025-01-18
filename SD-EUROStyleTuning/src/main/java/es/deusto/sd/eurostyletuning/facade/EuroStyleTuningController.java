package es.deusto.sd.eurostyletuning.facade;

import es.deusto.sd.eurostyletuning.assembler.EuroStyleTuningAssembler;
import es.deusto.sd.eurostyletuning.dao.PartRepository;
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
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eurostyle")
public class EuroStyleTuningController {

	private final EuroStyleTuningService euroStyleTuningService;
	private final EuroStyleTuningAssembler assembler;
	
	private final PartRepository partRepository;

	public EuroStyleTuningController(EuroStyleTuningService euroStyleTuningService,
			EuroStyleTuningAssembler assembler, PartRepository partRepository) {
		this.euroStyleTuningService = euroStyleTuningService;
		this.assembler = assembler;
		this.partRepository=partRepository;
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
	public ResponseEntity<List<PartDTO>> getPartsByBrandAndCategory(@RequestParam("brandId") int brandId,
			@RequestParam("categoryId") int categoryId) {
		List<Part> parts = euroStyleTuningService.retrieveParts(brandId, categoryId);
		if (parts.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		List<PartDTO> dtos = parts.stream().map(assembler::toPartDTO).collect(Collectors.toList());
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	// Procesar una compra
	// Procesar una compra
	@PostMapping("/purchases")
	public ResponseEntity<PurchaseResponseDTO> processPurchase(@RequestBody PurchaseRequestDTO request) {
	    // Buscar la pieza correspondiente por su ID
	    Optional<Part> optionalPart = partRepository.findById((int) request.getPartId());
	    if (!optionalPart.isPresent()) {
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }

	    Part part = optionalPart.get();

	    // Crear la entidad Purchase y asignar valores
	    Purchase purchase = new Purchase();
	    purchase.setPart(part);
	    purchase.setQuantity(request.getQuantity());
	    purchase.setShippingAddress(request.getShippingAddress());

	    // Procesar la compra a través del servicio
	    String result = euroStyleTuningService.processPurchase(purchase);

	    if ("Part not found".equals(result) || "Invalid quantity".equals(result)) {
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }

	    // Crear el DTO de respuesta
	    PurchaseResponseDTO response = new PurchaseResponseDTO(
	            purchase.getId(),
	            "Purchase processed successfully",
	            purchase.getPurchaseDate()
	    );

	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	// ZIL y GACK

	@GetMapping("/zil/parts")
	public List<Part> getAllPartsFromZIL() {
		return euroStyleTuningService.getAllPartsFromZIL();
	}

	@GetMapping("/zil/parts/filter")
	public List<Part> getPartsByBrandAndCategoryFromZIL(@RequestParam("brand") String brand,
			@RequestParam("category") String category) {
		return euroStyleTuningService.getPartsByBrandAndCategoryFromZIL(brand, category);
	}

	@GetMapping("/gack/parts")
	public List<Part> getAllPartsFromGack() {
		return euroStyleTuningService.getAllPartsFromGack();
	}

	@GetMapping("/gack/parts/filter")
	public List<Part> getPartsByBrandAndCategoryFromGack(@RequestParam("brand") String brandName,
			@RequestParam("category") String categoryName) {
		return euroStyleTuningService.getPartsByBrandAndCategoryFromGack(brandName, categoryName);
	}
}
