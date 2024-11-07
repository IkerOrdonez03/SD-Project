package es.deusto.sd.eurostyletuning.dto;

public class PurchaseRequestDTO {
	private long partId;
	private int quantity;
	private String shippingAddress;

	public PurchaseRequestDTO() {
	}

	public PurchaseRequestDTO(long partId, int quantity, String shippingAddress) {
		super();
		this.partId = partId;
		this.quantity = quantity;
		this.shippingAddress = shippingAddress;
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
}
