package Domain;

public class WishlistItem {
    private String userId;
    private String productId;
    private Products product;

    public WishlistItem(String userId, String productId, Products product) {
        this.userId = userId;
        this.productId = productId;
        this.product = product;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }
    // private User user;
}
