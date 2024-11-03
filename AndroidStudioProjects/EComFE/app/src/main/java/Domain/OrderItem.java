package Domain;

import java.util.ArrayList;

public class OrderItem {
    private int quantity;
    private double priceAtPurchase;
    private Product product;

    public OrderItem() {
    }

    public OrderItem(int quantity, double priceAtPurchase, Product products) {
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
        this.product = products;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(double priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product products) {
        this.product = products;
    }
}
