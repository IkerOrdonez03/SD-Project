package es.deusto.sd.zil.server;

import es.deusto.sd.zil.server.entity.Brand;
import es.deusto.sd.zil.server.entity.Category;
import es.deusto.sd.zil.server.entity.Part;
import es.deusto.sd.zil.server.service.ZILService;

public class DataInitializer {
	void initData(ZILService ZILService) {
			Brand audi = new Brand(1, "Audi");
			Brand ford = new Brand(2, "Ford");
			Brand nissan = new Brand(3, "Nissan");

			System.out.println("Brands created!");

			Category steeringWheel = new Category(1, "Steering Wheel");
			Category seatCover = new Category(2, "Seat Cover");
			Category carMat = new Category(3, "Car Mat");

			System.out.println("Categories created!");

			Part sportSteeringWheel = new Part(1, "Sport Steering Wheel", 150, steeringWheel, audi);
			Part premiumSeatCover = new Part(2, "Premium Seat Cover", 75, seatCover, ford);
			Part luxuryFloorMat = new Part(3, "Luxury Floor Mat", 120, carMat, nissan);

			ZILService.addPart(sportSteeringWheel);
			ZILService.addPart(premiumSeatCover);
			ZILService.addPart(luxuryFloorMat);

			System.out.println("Parts saved into the repository!");
	}
}
