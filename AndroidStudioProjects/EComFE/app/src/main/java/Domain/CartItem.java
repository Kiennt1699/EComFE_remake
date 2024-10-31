package Domain;

public class CartItem {
    private String cartId;         // ID của giỏ hàng
    private int quantity;          // Số lượng mặt hàng trong giỏ
    private CartProduct product;       // Thông tin sản phẩm

    // Constructor
    public CartItem(String cartId, int quantity, CartProduct product,boolean isChecked) {
        this.cartId = cartId;
        this.quantity = quantity;
        this.product = product;
        this.isChecked = isChecked;
    }
    private boolean isChecked; // Thêm thuộc tính này

    // Getter và setter cho thuộc tính isChecked
    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }


    // Getters and Setters
    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartProduct getProduct() {
        return product;
    }

    public void setProduct(CartProduct product) {
        this.product = product;
    }
}
