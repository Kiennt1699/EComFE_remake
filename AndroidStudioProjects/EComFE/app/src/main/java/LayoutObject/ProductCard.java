package LayoutObject;

import Domain.Products;

public class ProductCard {
    private Products item;
    private LoveButton loveButton;

    public ProductCard(Products item, LoveButton loveButton) {
        this.item = item;
        this.loveButton = loveButton;
    }

    public LoveButton getLoveButton() {
        return loveButton;
    }

    public void setLoveButton(LoveButton loveButton) {
        this.loveButton = loveButton;
    }

    public Products getItem() {
        return item;
    }

    public void setItem(Products item) {
        this.item = item;
    }
}
