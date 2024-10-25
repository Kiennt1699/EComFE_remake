package Domain;

public class AddToCartRequest {
    private String productId;
    private int quantity;
    private double priceAtAddition;

    public AddToCartRequest(String productId, int quantity, double priceAtAddition) {
        this.productId = productId;
        this.quantity = quantity;
        this.priceAtAddition = priceAtAddition;
    }

    // Getters
    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPriceAtAddition() {
        return priceAtAddition;
    }
}

