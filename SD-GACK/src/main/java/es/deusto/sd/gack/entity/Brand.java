package es.deusto.sd.gack.entity;

import java.util.Objects;

public class Brand {

	private int brandID;
	private String brandName;

	public Brand(int brandID, String brandName) {
		super();
		this.brandID = brandID;
		this.brandName = brandName;
	}

	public Brand() {

	}

	public int getBrandID() {
		return brandID;
	}

	public void setBrandID(int brandID) {
		this.brandID = brandID;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brandID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brand other = (Brand) obj;
		return brandID == other.brandID;
	}
}
