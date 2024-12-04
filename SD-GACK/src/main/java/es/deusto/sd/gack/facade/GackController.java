package es.deusto.sd.gack.facade;

import es.deusto.sd.gack.dto.PartDTO;
import es.deusto.sd.gack.entity.Part;
import es.deusto.sd.gack.assembler.GackAssembler;
import es.deusto.sd.gack.service.GackService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gack")
public class GackController {

	private final GackService gackService;
	private final GackAssembler assembler;

	public GackController(GackService gackService, GackAssembler assembler) {
		this.gackService = gackService;
		this.assembler = assembler;
	}

	// Obtener todas las partes
	@GetMapping("/parts")
	public ResponseEntity<List<PartDTO>> getAllParts() {
		List<Part> parts = gackService.getAllParts();
		if (parts.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		List<PartDTO> dtos = parts.stream().map(assembler::toPartDTO).collect(Collectors.toList());
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	// Obtener partes por marca y categoría
	@GetMapping("/parts/filter")
	public ResponseEntity<List<PartDTO>> getPartsByBrandAndCategory(@RequestParam("brandName") String brandName,
			@RequestParam("categoryName") String categoryName) {
		List<Part> parts = gackService.getPartsByBrandAndCategory(brandName, categoryName);
		if (parts.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		List<PartDTO> dtos = parts.stream().map(assembler::toPartDTO).collect(Collectors.toList());
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	// Agregar una nueva parte
	@PostMapping("/parts")
	public ResponseEntity<PartDTO> addPart(@RequestBody PartDTO partDTO) {
		Part part = new Part();
		part.setPartId(partDTO.getPartId());
		part.setDescription(partDTO.getDescription());
		part.setPrice(partDTO.getPrice());
		part.setCategory(partDTO.getCategory());
		part.setBrand(partDTO.getBrand());

		Part savedPart = gackService.addPart(part);
		return new ResponseEntity<>(assembler.toPartDTO(savedPart), HttpStatus.CREATED);
	}

	// Obtener una parte por ID
	@GetMapping("/parts/{id}")
	public ResponseEntity<PartDTO> getPartById(@PathVariable int id) {
		Part part = gackService.getPartById(id);
		if (part == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(assembler.toPartDTO(part), HttpStatus.OK);
	}

	// Actualizar una parte existente
	@PutMapping("/parts/{id}")
	public ResponseEntity<PartDTO> updatePart(@PathVariable("id") int id, @RequestBody PartDTO partDTO) {
		Part updatedPart = new Part();
		updatedPart.setDescription(partDTO.getDescription());
		updatedPart.setPrice(partDTO.getPrice());
		updatedPart.setCategory(partDTO.getCategory());
		updatedPart.setBrand(partDTO.getBrand());

		Part result = gackService.updatePart(id, updatedPart);
		if (result == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(assembler.toPartDTO(result), HttpStatus.OK);
	}

	// Eliminar una parte por ID
	@DeleteMapping("/parts/{id}")
	public ResponseEntity<Void> deletePart(@PathVariable("id") int id) {
		boolean isDeleted = gackService.deletePart(id);
		if (!isDeleted) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}