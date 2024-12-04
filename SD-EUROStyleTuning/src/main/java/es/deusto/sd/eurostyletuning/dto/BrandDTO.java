package es.deusto.sd.eurostyletuning.dto;

public class BrandDTO {

    private int brandID;
    private String brandName;

 
    public BrandDTO(int brandID, String brandName) {
        this.brandID = brandID;
        this.brandName = brandName;
    }


    public BrandDTO() {
    	
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
}