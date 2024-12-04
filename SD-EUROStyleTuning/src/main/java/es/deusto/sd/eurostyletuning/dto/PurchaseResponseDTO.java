package es.deusto.sd.eurostyletuning.dto;

import java.time.LocalDateTime;

public class PurchaseResponseDTO {
	private long purchaseId;
	private String message;
	private LocalDateTime purchaseDate;

	public PurchaseResponseDTO() {
	}

	public PurchaseResponseDTO(long purchaseId, String message, LocalDateTime purchaseDate) {
		this.purchaseId = purchaseId;
		this.message = message;
		this.purchaseDate = purchaseDate;
	}

	public long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
}
