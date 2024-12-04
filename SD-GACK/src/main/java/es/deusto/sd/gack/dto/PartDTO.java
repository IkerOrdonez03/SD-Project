package es.deusto.sd.gack.dto;

import es.deusto.sd.gack.entity.Brand;
import es.deusto.sd.gack.entity.Category;

public class PartDTO {
	private int partId;
	private String description;
	private int price;
	private String supplier = "GANK";
	private Category category;
	private Brand brand;

	public PartDTO() {
	}

	public PartDTO(int partId, String description, int price, Category category, Brand brand) {
		this.partId = partId;
		this.description = description;
		this.price = price;
		this.category = category;
		this.brand = brand;
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
	
	public String getSupplier() {
        return supplier;
    }

	public void setPrice(int price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
}
