package es.deusto.sd.eurostyletuning.dto;

public class CategoryDTO {

    private int categoryID;
    private String categoryName;

    
    public CategoryDTO(int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    
    public CategoryDTO() {
    	
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
