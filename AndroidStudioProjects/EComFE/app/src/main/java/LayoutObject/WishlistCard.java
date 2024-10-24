package LayoutObject;

import Domain.WishlistItem;

public class WishlistCard {
    private WishlistItem item;
    private LoveButton loveButton;

    public WishlistCard(WishlistItem item, LoveButton loveButton) {
        this.item = item;
        this.loveButton = loveButton;
    }

    public LoveButton getLoveButton() {
        return loveButton;
    }

    public void setLoveButton(LoveButton loveButton) {
        this.loveButton = loveButton;
    }

    public WishlistItem getItem() {
        return item;
    }

    public void setItem(WishlistItem item) {
        this.item = item;
    }
}
