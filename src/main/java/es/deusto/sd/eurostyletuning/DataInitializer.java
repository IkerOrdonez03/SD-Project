package es.deusto.sd.eurostyletuning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.deusto.sd.eurostyletuning.entity.Brand;
import es.deusto.sd.eurostyletuning.entity.Category;
import es.deusto.sd.eurostyletuning.entity.Part;
import es.deusto.sd.eurostyletuning.entity.Purchase;
import es.deusto.sd.eurostyletuning.service.EuroStyleTuningService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataInitializer {
	
    
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    
    /*
    Category original = new Category(10, "Brand-Original");
	Category tunning = new Category(17, "Street Flow");
	Category racing = new Category(17, "Racing");
	
	List<Category> categories = List.of(original, tunning, racing);
	
	for (Category c: categories) {
		tuningService.addCategory(c);
		logger.info("Categoria {" + c + "} guardada con éxito.");
	}
	
	Purchase purchase1 = new Purchase(123, 2L, 1, "Barakaldo", LocalDateTime.now());
	Purchase purchase2 = new Purchase(456, 2L, 3, "Bilbao", LocalDateTime.now());
	Purchase purchase3 = new Purchase(789, 3L, 2, "Vitoria", LocalDateTime.now());
	
	List<Purchase> purchases = new ArrayList<>();
	
	purchases.add(purchase1);
	purchases.add(purchase2);
	purchases.add(purchase3);
	
	
	for (Purchase p: purchases) {
		tuningService.addPurchase(P);
		logger.info("Categoria {" + p + "} guardada con éxito.");
	}

     

//    @Bean
    CommandLineRunner initData(EuroStyleTuningService tuningService) {
        return args -> {
            // Crear algunas marcas de automóviles
            CarBrand audi = new CarBrand(1L, "Audi");
            CarBrand ford = new CarBrand(2L, "Ford");
            CarBrand nissan = new CarBrand(3L, "Nissan");

            // Simulación de almacenamiento de marcas en el servicio
            List<CarBrand> brands = List.of(audi, ford, nissan);
            brands.forEach(brand -> {
                tuningService.addBrand(brand); // Almacena en el servicio
                logger.info("Marca guardada: " + brand.getName());
            });

            // Crear piezas para cada marca
            Part steeringWheel = new Part(1L, "Steering Wheel", 150.00, 10, audi.getId());
            Part seatCushion = new Part(2L, "Seat Cushion", 75.00, 5, ford.getId());
            Part carMat = new Part(3L, "Car Mat", 50.00, 15, nissan.getId());

            // Simulación de almacenamiento de piezas en el servicio
            List<Part> parts = List.of(steeringWheel, seatCushion, carMat);
            parts.forEach(part -> {
                tuningService.addPart(part); // Almacena en el servicio
                logger.info("Pieza guardada: " + part.getName() + ", precio: " + part.getPrice());
            });

            logger.info("Datos iniciales cargados para EuroStyleTuning!");
        };
    }
    */
}
