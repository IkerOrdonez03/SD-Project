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

@Configuration
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    CommandLineRunner initData(EuroStyleTuningService euroStyleTuningService) {
        return args -> {
            // Crear algunas marcas
            Brand bmw = new Brand(1, "BMW");
            Brand audi = new Brand(2, "Audi");
            Brand mercedes = new Brand(3, "Mercedes");

            euroStyleTuningService.addBrand(bmw);
            euroStyleTuningService.addBrand(audi);
            euroStyleTuningService.addBrand(mercedes);
            logger.info("Brands saved!");

            // Crear algunas categorías
            Category engine = new Category(1, "Engine");
            Category suspension = new Category(2, "Suspension");
            Category exhaust = new Category(3, "Exhaust");

            euroStyleTuningService.addCategory(engine);
            euroStyleTuningService.addCategory(suspension);
            euroStyleTuningService.addCategory(exhaust);
            logger.info("Categories saved!");

            // Crear y agregar partes al repositorio directamente
            Part turboCharger = new Part(1, "Turbocharger Kit", 2500, "TurboWorks", engine, bmw);
            Part coilovers = new Part(2, "Performance Coilovers", 1500, "Suspension Pro", suspension, audi);
            Part exhaustSystem = new Part(3, "Sport Exhaust System", 1200, "ExhaustX", exhaust, mercedes);

            euroStyleTuningService.addPart(turboCharger);
            euroStyleTuningService.addPart(coilovers);
            euroStyleTuningService.addPart(exhaustSystem);

            logger.info("Parts saved!");

            // Crear algunas compras
            Purchase purchase1 = new Purchase(1, turboCharger.getPartId(), 2, "123 Street, City", LocalDateTime.now());
            Purchase purchase2 = new Purchase(2, coilovers.getPartId(), 1, "456 Avenue, City", LocalDateTime.now());
            Purchase purchase3 = new Purchase(3, exhaustSystem.getPartId(), 3, "789 Boulevard, City", LocalDateTime.now());

            euroStyleTuningService.addPurchase(purchase1);
            euroStyleTuningService.addPurchase(purchase2);
            euroStyleTuningService.addPurchase(purchase3);
            logger.info("Purchases saved!");
        };
    }
}