package org.shoppingservicev2.service;

import org.shoppingservicev2.domain.Auction;
import org.shoppingservicev2.domain.AuctionFactory;
import org.shoppingservicev2.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuctionCrud {

    @Autowired
    AuctionRepository auctionRepository;

    public Response createAuction(AuctionDto auctionDto) {
        Response response = getResponse(auctionDto);
        if (response != null) return response;
        auctionRepository.save(new AuctionFactory().createFrom(auctionDto));
        return Response.aSuccessfulResponseWith("Auction created");
    }

    private Response getResponse(AuctionDto auctionDto) {
        if (auctionDto.getTitle().equals(""))
            return Response.aFailureResponse("field title is empty");
        if (auctionDto.getDescritpion().equals(""))
            return Response.aFailureResponse("field description is empty");
        if (auctionDto.getCategoryId() <= 0)
            return Response.aFailureResponse("Wrong category selected");
        if (auctionDto.getOwnerId() <= 0)
            return Response.aFailureResponse("Wrong owner id selected");
        if (auctionDto.getPrice() == null)
            return Response.aFailureResponse("Auction without price");
        if (auctionDto.getPrice().compareTo(new BigDecimal(0)) <= 0)
            return Response.aFailureResponse("Price must be higher than zero");
        return null;
    }

    public List<Auction> getAllAuctions() {
        Iterable<Auction> result = auctionRepository.findAll();
        List<Auction> listOfAllAuctions = new ArrayList<>();
        result.forEach(listOfAllAuctions::add);
        return listOfAllAuctions;
    }
}
