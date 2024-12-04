package es.deusto.sd.gack.entity;

import java.util.Objects;

public class Part {
	private int partId;
	private String description;
	private int price;
	private int costPrice;
	private String supplier = "GACK";
	private Category category;
	private Brand brand;

	public Part(int partId, String description, int price, int costPrice, Category category, Brand brand) {
		this.partId = partId;
		this.description = description;
		this.price = price;
		this.costPrice = costPrice;
		this.category = category;
		this.brand = brand;
	}

	public Part() {
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

	public int getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(int costPrice) {
		this.costPrice = costPrice;
	}

	public String getSupplier() {
		return supplier;
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

	@Override
	public int hashCode() {
		return Objects.hash(partId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Part other = (Part) obj;
		return partId == other.partId;
	}
}
