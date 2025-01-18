package es.deusto.sd.eurostyletuning;

import es.deusto.sd.eurostyletuning.dao.BrandRepository;
import es.deusto.sd.eurostyletuning.dao.CategoryRepository;
import es.deusto.sd.eurostyletuning.dao.PartRepository;
import es.deusto.sd.eurostyletuning.dao.PurchaseRepository;
import es.deusto.sd.eurostyletuning.entity.Brand;
import es.deusto.sd.eurostyletuning.entity.Category;
import es.deusto.sd.eurostyletuning.entity.Part;
import es.deusto.sd.eurostyletuning.entity.Purchase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final PartRepository partRepository;
    private final PurchaseRepository purchaseRepository;

    public DataInitializer(BrandRepository brandRepository, CategoryRepository categoryRepository, 
                           PartRepository partRepository, PurchaseRepository purchaseRepository) {
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.partRepository = partRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Crear y guardar categorías
        Category category1 = new Category();
        category1.setCategoryName("Motor");
        categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setCategoryName("Suspensión");
        categoryRepository.save(category2);

        // Crear y guardar marcas
        Brand brand1 = new Brand();
        brand1.setBrandName("Bosch");
        brandRepository.save(brand1);

        Brand brand2 = new Brand();
        brand2.setBrandName("Bilstein");
        brandRepository.save(brand2);

        // Crear y guardar piezas
        Part part1 = new Part();
        part1.setDescription("Filtro de aire");
        part1.setPrice(50);
        part1.setSupplier("ZIL");
        part1.setCategory(category1);
        part1.setBrand(brand1);
        partRepository.save(part1);

        Part part2 = new Part();
        part2.setDescription("Amortiguadores");
        part2.setPrice(200);
        part2.setSupplier("GACK");
        part2.setCategory(category2);
        part2.setBrand(brand2);
        partRepository.save(part2);

        // Crear y guardar compras
        Purchase purchase1 = new Purchase();
        purchase1.setPart(part1);
        purchase1.setQuantity(2);
        purchase1.setShippingAddress("Calle Falsa 123");
        purchase1.setPurchaseDate(LocalDateTime.now());
        purchaseRepository.save(purchase1);

        Purchase purchase2 = new Purchase();
        purchase2.setPart(part2);
        purchase2.setQuantity(1);
        purchase2.setShippingAddress("Avenida Siempre Viva 742");
        purchase2.setPurchaseDate(LocalDateTime.now());
        purchaseRepository.save(purchase2);

        System.out.println("Datos de ejemplo insertados correctamente.");
    }
}
