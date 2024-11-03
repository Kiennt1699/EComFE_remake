package Domain;


import java.sql.Timestamp;
import java.util.Date;

public class PurchasedProduct {
    private String productName;
    private String productId;
    private String status;
    private String productType;
    private int quantity;
    private Date purchaseDate;
    private String price;
    private String productImage;
    private String productDLocation;
    private String productPayMethod;
    private Date reciveTime;

    public PurchasedProduct(String productName, String productId, String status, String productType, int quantity, Date purchaseDate, String price, String productImage, String productDLocation, String productPayMethod, Date reciveTime) {
        this.productName = productName;
        this.productId = productId;
        this.status = status;
        this.productType = productType;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.productImage = productImage;
        this.productDLocation = productDLocation;
        this.productPayMethod = productPayMethod;
        this.reciveTime = reciveTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDLocation() {
        return productDLocation;
    }

    public void setProductDLocation(String productDLocation) {
        this.productDLocation = productDLocation;
    }

    public String getProductPayMethod() {
        return productPayMethod;
    }

    public void setProductPayMethod(String productPayMethod) {
        this.productPayMethod = productPayMethod;
    }

    public Date getReciveTime() {
        return reciveTime;
    }

    public void setReciveTime(Timestamp reciveTime) {
        this.reciveTime = reciveTime;
    }
}
