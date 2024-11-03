package Domain;

public class CheckoutRequest {
    private String address;
    private int paymentMethod;

    public CheckoutRequest(String address, int paymentMethod) {
        this.address = address;
        this.paymentMethod = paymentMethod;
    }

    // Getters và setters (nếu cần)
}