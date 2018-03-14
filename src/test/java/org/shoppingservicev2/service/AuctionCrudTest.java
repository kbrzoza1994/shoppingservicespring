package org.shoppingservicev2.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.shoppingservicev2.domain.Auction;
import org.shoppingservicev2.domain.AuctionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuctionCrudTest {

    @Autowired
    AuctionCrud auctionCrud;

    @Test
    public void shouldAddAuction(){
        AuctionDto auctionDto = new AuctionDto();
        auctionDto.setBuyerId(1);
        auctionDto.setOwnerId(2);
        auctionDto.setCategoryId(78);
        auctionDto.setTitle("title");
        auctionDto.setDescription("description");
        auctionDto.setPrice(new BigDecimal(455));
        Response response = auctionCrud.createAuction(auctionDto);

        Assert.assertTrue(response.isSuccess());
        Assert.assertEquals(response.getMessage(), "Auction created");
    }

    @Test
    public void shouldNotAddAuctionWhenNoTitlePassed(){
        AuctionDto auctionDto = new AuctionDto();
        auctionDto.setBuyerId(1);
        auctionDto.setOwnerId(2);
        auctionDto.setCategoryId(78);
        auctionDto.setDescription("description");
        auctionDto.setPrice(new BigDecimal(455));
        Response response = auctionCrud.createAuction(auctionDto);

        Assert.assertFalse(response.isSuccess());
        Assert.assertEquals(response.getMessage(), "field title is empty");
    }

    @Test
    public void shouldNotAddAuctionWhenNoDescriptionPassed(){
        AuctionDto auctionDto = new AuctionDto();
        auctionDto.setBuyerId(1);
        auctionDto.setOwnerId(2);
        auctionDto.setCategoryId(78);
        auctionDto.setTitle("title");
        auctionDto.setPrice(new BigDecimal(455));
        Response response = auctionCrud.createAuction(auctionDto);

        Assert.assertFalse(response.isSuccess());
        Assert.assertEquals(response.getMessage(), "field description is empty");
    }

    @Test
    public void shouldNotAddAuctionWithNoCategoryId(){
        AuctionDto auctionDto = new AuctionDto();
        auctionDto.setBuyerId(1);
        auctionDto.setOwnerId(2);
        auctionDto.setTitle("title");
        auctionDto.setDescription("description");
        auctionDto.setPrice(new BigDecimal(455));
        Response response = auctionCrud.createAuction(auctionDto);

        Assert.assertFalse(response.isSuccess());
        Assert.assertEquals(response.getMessage(), "Wrong category selected");
    }

    @Test
    public void shouldNotAddAuctionWhenNoOwnerIdPassed(){
        AuctionDto auctionDto = new AuctionDto();
        auctionDto.setBuyerId(1);
        auctionDto.setCategoryId(78);
        auctionDto.setTitle("title");
        auctionDto.setDescription("description");
        auctionDto.setPrice(new BigDecimal(455));
        Response response = auctionCrud.createAuction(auctionDto);

        Assert.assertFalse(response.isSuccess());
        Assert.assertEquals(response.getMessage(), "Wrong owner id selected");
    }

    @Test
    public void shouldNotAddAuctionWhenNoPricePassed(){
        AuctionDto auctionDto = new AuctionDto();
        auctionDto.setBuyerId(1);
        auctionDto.setOwnerId(2);
        auctionDto.setCategoryId(78);
        auctionDto.setTitle("title");
        auctionDto.setDescription("description");
        Response response = auctionCrud.createAuction(auctionDto);

        Assert.assertFalse(response.isSuccess());
        Assert.assertEquals(response.getMessage(), "Auction without price");
    }

    @Test
    public void shouldNotAddAuctionWhenZeroPricePassed(){
        AuctionDto auctionDto = new AuctionDto();
        auctionDto.setBuyerId(1);
        auctionDto.setOwnerId(2);
        auctionDto.setCategoryId(78);
        auctionDto.setTitle("title");
        auctionDto.setDescription("description");
        auctionDto.setPrice(new BigDecimal(0));
        Response response = auctionCrud.createAuction(auctionDto);

        Assert.assertFalse(response.isSuccess());
        Assert.assertEquals(response.getMessage(), "Price must be higher than zero");
    }

    @Test
    public void shouldNotAddAuctionWhenMinusPricePassed(){
        AuctionDto auctionDto = new AuctionDto();
        auctionDto.setBuyerId(1);
        auctionDto.setOwnerId(2);
        auctionDto.setCategoryId(78);
        auctionDto.setTitle("title");
        auctionDto.setDescription("description");
        auctionDto.setPrice(new BigDecimal(-10));
        Response response = auctionCrud.createAuction(auctionDto);

        Assert.assertFalse(response.isSuccess());
        Assert.assertEquals(response.getMessage(), "Price must be higher than zero");
    }

    @Test
    public void shouldNotAddAuctionWhenNegativeOwnerIdPassed(){
        AuctionDto auctionDto = new AuctionDto();
        auctionDto.setBuyerId(1);
        auctionDto.setOwnerId(-2);
        auctionDto.setCategoryId(78);
        auctionDto.setTitle("title");
        auctionDto.setDescription("description");
        auctionDto.setPrice(new BigDecimal(10));
        Response response = auctionCrud.createAuction(auctionDto);

        Assert.assertFalse(response.isSuccess());
        Assert.assertEquals(response.getMessage(), "Wrong owner id selected");
    }

    @Test
    public void shouldNotAddAuctionWhenNegativeCategoryIdPassed(){
        AuctionDto auctionDto = new AuctionDto();
        auctionDto.setBuyerId(1);
        auctionDto.setOwnerId(2);
        auctionDto.setCategoryId(-78);
        auctionDto.setTitle("title");
        auctionDto.setDescription("description");
        auctionDto.setPrice(new BigDecimal(10));
        Response response = auctionCrud.createAuction(auctionDto);

        Assert.assertFalse(response.isSuccess());
        Assert.assertEquals(response.getMessage(), "Wrong category id selected");
    }

    @Test
    public void shouldFindAllAuctions(){
        AuctionDto auctionDto = new AuctionDto();
        auctionDto.setBuyerId(1);
        auctionDto.setOwnerId(2);
        auctionDto.setCategoryId(78);
        auctionDto.setTitle("title");
        auctionDto.setDescription("description");
        auctionDto.setPrice(new BigDecimal(10));
        auctionCrud.createAuction(auctionDto);
        Auction auction = new AuctionFactory().createFrom(auctionDto);
        auctionDto.setDescription("some other description");
        auctionDto.setTitle("some title");
        auctionCrud.createAuction(auctionDto);
        Auction auction1 = new AuctionFactory().createFrom(auctionDto);
        auctionDto.setOwnerId(7);
        auctionDto.setTitle("title some other");
        auctionCrud.createAuction(auctionDto);
        Auction auction2 = new AuctionFactory().createFrom(auctionDto);
        List<Auction> listOfAllAuctions = auctionCrud.getAllAuctions();

        Assert.assertTrue(listOfAllAuctions.size()==3);
        Assert.assertEquals(listOfAllAuctions.get(0).getTitle(), auction.getTitle());
        Assert.assertEquals(listOfAllAuctions.get(1).getTitle(), auction1.getTitle());
        Assert.assertEquals(listOfAllAuctions.get(2).getTitle(), auction2.getTitle());
    }

}
