package Domain;

public class CartProduct {
    private String productId;
    private String name;
    private String imageUrl;
    private double price;
    private String description;
    private int quantityAvailable;


    public CartProduct(String productId, String name, String imageUrl, double price, String description, int quantityAvailable) {
        this.productId = productId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.description = description;
        this.quantityAvailable = quantityAvailable;
    }
    // Getters and Setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }
}
