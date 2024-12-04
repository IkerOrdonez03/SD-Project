package es.deusto.sd.gack;

import es.deusto.sd.gack.entity.Brand;
import es.deusto.sd.gack.entity.Category;
import es.deusto.sd.gack.entity.Part;
import es.deusto.sd.gack.service.GackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

	private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

	@Bean
	CommandLineRunner initData(GackService gackService) {
		return args -> {
			Brand audi = new Brand(1, "Audi");
			Brand ford = new Brand(2, "Ford");
			Brand nissan = new Brand(3, "Nissan");

			logger.info("Brands created!");

			Category steeringWheel = new Category(1, "Steering Wheel");
			Category seatCover = new Category(2, "Seat Cover");
			Category carMat = new Category(3, "Car Mat");

			logger.info("Categories created!");

			Part sportSteeringWheel = new Part(1, "Sport Steering Wheel", 150, 100, steeringWheel, audi);
			Part premiumSeatCover = new Part(2, "Premium Seat Cover", 75, 50, seatCover, ford);
			Part luxuryFloorMat = new Part(3, "Luxury Floor Mat", 120, 80, carMat, nissan);

			gackService.addPart(sportSteeringWheel);
			gackService.addPart(premiumSeatCover);
			gackService.addPart(luxuryFloorMat);

			logger.info("Parts saved into the repository!");
		};
	}
}