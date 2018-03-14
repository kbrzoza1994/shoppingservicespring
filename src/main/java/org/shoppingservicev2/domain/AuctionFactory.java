package org.shoppingservicev2.domain;

import org.shoppingservicev2.service.AuctionDto;

public class AuctionFactory {
    public Auction createFrom(AuctionDto auctionDto) {
        return AuctionBuilder(auctionDto);
    }

    private Auction AuctionBuilder(AuctionDto auctionDto) {
        return new Auction.AuctionBuilder()
                .withTitle(auctionDto.getTitle())
                .withDescription(auctionDto.getDescritpion())
                .withPrice(auctionDto.getPrice())
                .withCategoryId(auctionDto.getCategoryId())
                .withOwnerId(auctionDto.getOwnerId())
                .withBuyerId(auctionDto.getBuyerId())
                .build();

    }
}
