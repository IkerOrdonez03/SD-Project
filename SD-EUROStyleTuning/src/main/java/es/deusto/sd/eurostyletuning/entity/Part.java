package es.deusto.sd.eurostyletuning.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Part implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int partId;

    private String description;

    private int price;

    private String supplier;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false) // Relación con Category
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false) // Relación con Brand
    private Brand brand;

    public Part(int partId, String description, int price, String supplier, Category category, Brand brand) {
        this.partId = partId;
        this.description = description;
        this.price = price;
        this.supplier = supplier;
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

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
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
