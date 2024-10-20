package Domain;

public class Products {
    private String name;
    private double price;
    private int rating;
    private String imageUrl;

    // Getters and Setters
    public String getProductName() {
        return name;
    }

    public void setProductName(String productName) {
        this.name = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}