package org.shoppingservicev2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Auction {

    @Id
    @GeneratedValue
    private int id;
    private int categoryId;
    private int ownerId;
    private int buyerId;
    private BigDecimal price;
    private String title;
    private String description;
    private int bidCounter;

    public Auction() {
    }

    private Auction(AuctionBuilder auctionBuilder) {
        this.categoryId = auctionBuilder.categoryId;
        this.ownerId = auctionBuilder.ownerId;
        this.buyerId = auctionBuilder.buyerId;
        this.price = auctionBuilder.price;
        this.title = auctionBuilder.title;
        this.description = auctionBuilder.description;
        this.bidCounter = auctionBuilder.bidCounter;
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

    public String getDescription() {
        return description;
    }

    public int getBidCounter() {
        return bidCounter;
    }

    public static class AuctionBuilder {

        private int categoryId;
        private int ownerId;
        private int buyerId;
        private BigDecimal price;
        private String title;
        private String description;
        private int bidCounter = 0;

        public AuctionBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public AuctionBuilder withDescription(String descritpion) {
            this.description = descritpion;
            return this;
        }

        public AuctionBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public AuctionBuilder withCategoryId(int categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public AuctionBuilder withOwnerId(int ownerId) {
            this.ownerId = ownerId;
            return this;
        }

        public AuctionBuilder withBuyerId(int buyerId) {
            this.buyerId = buyerId;
            return this;
        }

        public Auction build(){
            return new Auction(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auction auction = (Auction) o;
        return id == auction.id &&
                categoryId == auction.categoryId &&
                ownerId == auction.ownerId &&
                buyerId == auction.buyerId &&
                bidCounter == auction.bidCounter &&
                Objects.equals(price, auction.price) &&
                Objects.equals(title, auction.title) &&
                Objects.equals(description, auction.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, categoryId, ownerId, buyerId, price, title, description, bidCounter);
    }


}
