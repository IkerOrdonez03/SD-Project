package es.deusto.sd.eurostyletuning.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Purchase {
	private long id;
	private long partId;
	private int quantity;
	private String shippingAddress;
	private LocalDateTime purchaseDate;

	public Purchase() {
	}

	public Purchase(long id, long partId, int quantity, String shippingAddress, LocalDateTime purchaseDate) {
		super();
		this.id = id;
		this.partId = partId;
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

	public long getPartId() {
		return partId;
	}

	public void setPartId(long partId) {
		this.partId = partId;
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
		return "Purchase [id=" + id + ", partId=" + partId + ", quantity=" + quantity + ", shippingAddress="
				+ shippingAddress + ", purchaseDate=" + purchaseDate + "]";
	}

}
