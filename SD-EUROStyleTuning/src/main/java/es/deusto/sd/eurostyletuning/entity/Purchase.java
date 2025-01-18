package es.deusto.sd.eurostyletuning.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "part_id", nullable = false) // Relación con Part
    private Part part;

    private int quantity;

    private String shippingAddress;

    private LocalDateTime purchaseDate;

    public Purchase() {
    }

    public Purchase(long id, Part part, int quantity, String shippingAddress, LocalDateTime purchaseDate) {
        this.id = id;
        this.part = part;
        this.quantity = quantity;
        this.shippingAddress = shippingAddress;
        this.purchaseDate = purchaseDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Purchase other = (Purchase) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return "Purchase [id=" + id + ", part=" + part + ", quantity=" + quantity + ", shippingAddress="
                + shippingAddress + ", purchaseDate=" + purchaseDate + "]";
    }
}
