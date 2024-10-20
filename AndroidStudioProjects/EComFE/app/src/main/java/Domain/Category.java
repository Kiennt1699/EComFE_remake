package Domain;

public class Category {
    public boolean isDeleted;
    public String createdAt;
    public String updatedAt;
    public int categoryId;
    public String name;
    public String description;
    public Integer parentCategoryId;
    public Category parentCategory;

    // Constructor
    public Category(int categoryId, String name, String description, boolean isDeleted, String createdAt, String updatedAt, Integer parentCategoryId, Category parentCategory) {

    }
}

