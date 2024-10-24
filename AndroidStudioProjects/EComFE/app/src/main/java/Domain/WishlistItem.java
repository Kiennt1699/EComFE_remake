package Domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class WishlistItem implements Parcelable {
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

    protected WishlistItem(Parcel in) {
        productId = in.readString();
        product = in.readParcelable(Products.class.getClassLoader());
        userId = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flag) {
        dest.writeString(productId);
        dest.writeParcelable(product, flag);
        dest.writeString(userId);
    }
    public static final Creator<WishlistItem> CREATOR = new Creator<WishlistItem>() {
        @Override
        public WishlistItem createFromParcel(Parcel in) {
            return new WishlistItem(in);
        }

        @Override
        public WishlistItem[] newArray(int size) {
            return new WishlistItem[size];
        }
    };
}
