package org.shoppingservicev2.service;

import java.math.BigDecimal;

public class AuctionDto {
    private static final String NO_STRING_VALUE = "";
    private static final int NO_INT_VALUE = 0;
    private static final BigDecimal NO_BIGDECIMAL_VALUE = null;

    private int categoryId = NO_INT_VALUE;
    private int ownerId = NO_INT_VALUE;
    private int buyerId = NO_INT_VALUE;
    private BigDecimal price = NO_BIGDECIMAL_VALUE;
    private String title = NO_STRING_VALUE;
    private String description = NO_STRING_VALUE;

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getDescritpion() {
        return description;
    }
}
