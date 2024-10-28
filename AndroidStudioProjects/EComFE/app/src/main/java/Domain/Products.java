package Domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Products implements Parcelable {
    private String productId;
    private String name;
    private String description;
    private double price;
    private int quantityAvailable;
    private String categoryId;
    private String imageUrl;
    private boolean isDeleted;
    private String createdAt;
    private String updatedAt;
    private int rating;

    public boolean isWishlisted() {
        return isWishlisted;
    }

    public void setWishlisted(boolean wishlisted) {
        isWishlisted = wishlisted;
    }

    private boolean isWishlisted;

    // Getters and Setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return name;
    }

    public void setProductName(String productName) {
        this.name = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    // Parcelable implementation
    protected Products(Parcel in) {
        productId = in.readString();
        name = in.readString();
        description = in.readString();
        price = in.readDouble();
        quantityAvailable = in.readInt();
        categoryId = in.readString();
        imageUrl = in.readString();
        isDeleted = in.readByte() != 0; // Read boolean as byte
        createdAt = in.readString();
        updatedAt = in.readString();
        rating = in.readInt();
        isWishlisted = in.readByte() != 0;
    }

    public static final Creator<Products> CREATOR = new Creator<Products>() {
        @Override
        public Products createFromParcel(Parcel in) {
            return new Products(in);
        }

        @Override
        public Products[] newArray(int size) {
            return new Products[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productId);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeDouble(price);
        dest.writeInt(quantityAvailable);
        dest.writeString(categoryId);
        dest.writeString(imageUrl);
        dest.writeByte((byte) (isDeleted ? 1 : 0)); // Write boolean as byte
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeInt(rating);
        dest.writeByte((byte)(isWishlisted ? 1 : 0));
    }
}