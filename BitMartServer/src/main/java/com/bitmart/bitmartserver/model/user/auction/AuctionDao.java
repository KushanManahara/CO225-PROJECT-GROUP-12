package com.bitmart.bitmartserver.model.user.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuctionDao {

    @Autowired
    private AuctionRepository repository;

    public void save(Auction auction){
        repository.save(auction);
    }

    public Auction getAllAuctions(){
        List<Auction> auctions = new ArrayList<>();
        Auction auction = new Auction();
        Streamable.of(repository.findAll())
                .forEach(auctions::add);
        System.out.println("returned all auctions");
        for (Auction a: auctions) {
            if(a.getAuctionStatus().equals("OnGoing")){
                return a;
            }
        }
        return auction;
    }
}
