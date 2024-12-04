package es.deusto.sd.eurostyletuning.dto;


public class PartDTO {

    private int partId;
    private String description;
    private int price;
    private String supplier;
    private CategoryDTO category;
    private BrandDTO brand;

    
    public PartDTO(int partId, String description, int price, String supplier, CategoryDTO category, BrandDTO brand) {
        this.partId = partId;
        this.description = description;
        this.price = price;
        this.supplier = supplier;
        this.category = category;
        this.brand = brand;
    }

    
    public PartDTO() {
    	
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public BrandDTO getBrand() {
        return brand;
    }

    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }
}