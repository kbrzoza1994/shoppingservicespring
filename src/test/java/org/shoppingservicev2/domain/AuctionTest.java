package org.shoppingservicev2.domain;

import org.junit.Assert;
import org.junit.Test;
import org.shoppingservicev2.service.AuctionDto;
import org.shoppingservicev2.service.UserDto;

import java.math.BigDecimal;

public class AuctionTest {

    @Test
    public void shouldCreateAuction() {
        AuctionDto auctionDto = new AuctionDto();
        auctionDto.setCategoryId(7);
        auctionDto.setOwnerId(48);
        auctionDto.setBuyerId(41);
        auctionDto.setPrice(new BigDecimal(777));
        auctionDto.setTitle("Brand New Watch");
        auctionDto.setDescription("New* Watch");

        Auction auction = new AuctionFactory().createFrom(auctionDto);

        Assert.assertEquals(auctionDto.getCategoryId(), auction.getCategoryId());
        Assert.assertEquals(auctionDto.getOwnerId(), auction.getOwnerId());
        Assert.assertEquals(auctionDto.getBuyerId(), auction.getBuyerId());
        Assert.assertEquals(auctionDto.getPrice(), auction.getPrice());
        Assert.assertEquals(auctionDto.getTitle(), auction.getTitle());
        Assert.assertEquals(auctionDto.getDescritpion(), auction.getDescription());
        Assert.assertEquals(auction.getBidCounter(), 0);
    }
}
