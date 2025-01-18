package es.deusto.sd.eurostyletuning.service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import es.deusto.sd.eurostyletuning.assembler.EuroStyleTuningAssembler;
import es.deusto.sd.eurostyletuning.dao.BrandRepository;
import es.deusto.sd.eurostyletuning.dao.CategoryRepository;
import es.deusto.sd.eurostyletuning.dao.PartRepository;
import es.deusto.sd.eurostyletuning.dao.PurchaseRepository;
import es.deusto.sd.eurostyletuning.dto.PartDTO;
import es.deusto.sd.eurostyletuning.entity.*;
import es.deusto.sd.eurostyletuning.external.EuroStyleGatewayFactory;
import es.deusto.sd.eurostyletuning.external.IGateway;
import es.deusto.sd.eurostyletuning.external.ZILServiceGateway;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EuroStyleTuningServiceImpl implements EuroStyleTuningService {
	
	@Autowired
    private EuroStyleTuningAssembler assembler;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
    private CategoryRepository categoryRepository;
	
	@Autowired
    private PurchaseRepository purchaseRepository;
	
	@Autowired
    private PartRepository partRepository;
	
	@Autowired
	private EuroStyleGatewayFactory euroStyleGatewayFactory;
	
    private JavaMailSender mailSender;

    public List<Category> getCategories() {
    	return categoryRepository.findAll();
		
	}
	
	public List<Brand> getCarBrands(){
		return brandRepository.findAll();
	}
	
	@Override
	public List<Purchase> getPurchases() {
		return purchaseRepository.findAll();
	}
	

	@Override
	public Brand getBrandById(long id) {
        return brandRepository.findById((int) id).orElse(null);
    }

	@Override
	public Category getCategoryById(long id) {
        return categoryRepository.findById((int) id).orElse(null);
    }
	
	@Override
	public Purchase getPurchaseById(long id) {
        return purchaseRepository.findById(id).orElse(null);
    }
	
	@Override
	public List<Part> retrieveParts(int brandId, int categoryId) {
        return partRepository.findAll().stream()
            .filter(part -> part.getBrand().getBrandID() == brandId && part.getCategory().getCategoryID() == categoryId)
            .collect(Collectors.toList());
    }
	
	@Override
	public String processPurchase(Purchase purchaseRequest) {
        Part part = partRepository.findById(purchaseRequest.getPart().getPartId()).orElse(null);
        if (part == null) {
            return "Part not found";
        }
        if (purchaseRequest.getQuantity() <= 0) {
            return "Invalid quantity";
        }

        purchaseRequest.setPurchaseDate(LocalDateTime.now());
        purchaseRepository.save(purchaseRequest);
        return "Purchase processed successfully";
    }

	@Override
	public void addBrand(Brand brand) {
        if (brand != null) {
            brandRepository.save(brand);
        }
    }
	
	@Override
	public void addCategory(Category category) {
        if (category != null) {
            categoryRepository.save(category);
        }
    }

	@Override
	public void addPurchase(Purchase purchase) {
        purchase.setPurchaseDate(LocalDateTime.now());
        purchaseRepository.save(purchase);

        String buyerEmail = "javier.g@opendeusto.es";
        String subject = "Purchase Confirmation";
        String text = String.format("Dear buyer,\n\nYour purchase with ID [%d] has been confirmed.", purchase.getId());

        sendEmail(buyerEmail, subject, text);
    }
	
	@Override
	public void addPart(Part part) {
        if (part != null) {
            partRepository.save(part);
        }
    }
	
	// Funciones para ZIL y GACK

    public List<Part> getAllPartsFromZIL() {
    	IGateway zilGateway = euroStyleGatewayFactory.createGateway(EuroStyleGatewayFactory.GatewayType.ZIL);
        Optional<List<PartDTO>> optionalPartDTOs = zilGateway.getParts();

        return optionalPartDTOs
                .map(partDTOs -> partDTOs.stream()
                        .map(assembler::toPart)
                        .collect(Collectors.toList()))
                .orElse(List.of());
    }

    public List<Part> getPartsByBrandAndCategoryFromZIL(String brandName, String categoryName) {
    	IGateway zilGateway = euroStyleGatewayFactory.createGateway(EuroStyleGatewayFactory.GatewayType.ZIL);
        Optional<List<PartDTO>> optionalPartDTOs = zilGateway.getPartsByBrandAndCategory(brandName, categoryName);

        return optionalPartDTOs
                .map(partDTOs -> partDTOs.stream()
                        .map(assembler::toPart)
                        .collect(Collectors.toList()))
                .orElse(List.of());
    }
    
    public List<Part> getAllPartsFromGack() {
    	IGateway gackGateway = euroStyleGatewayFactory.createGateway(EuroStyleGatewayFactory.GatewayType.GACK);
        Optional<List<PartDTO>> optionalPartDTOs = gackGateway.getParts();

        return optionalPartDTOs
                .map(partDTOs -> partDTOs.stream()
                        .map(assembler::toPart)
                        .collect(Collectors.toList()))
                .orElse(List.of());
    }

    public List<Part> getPartsByBrandAndCategoryFromGack(String brandName, String categoryName) {
    	IGateway gackGateway = euroStyleGatewayFactory.createGateway(EuroStyleGatewayFactory.GatewayType.GACK);
        Optional<List<PartDTO>> optionalPartDTOs = gackGateway.getPartsByBrandAndCategory(brandName, categoryName);

        return optionalPartDTOs
                .map(partDTOs -> partDTOs.stream()
                        .map(assembler::toPart)
                        .collect(Collectors.toList()))
                .orElse(List.of());
    }
    
    public void sendEmail(String to, String subject, String text) {
    	
    	SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
	}
}