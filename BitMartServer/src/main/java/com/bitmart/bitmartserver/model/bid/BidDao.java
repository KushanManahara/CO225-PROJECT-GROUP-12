package com.bitmart.bitmartserver.model.bid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidDao {

    @Autowired
    private BidRepository repository;

    public void save(Bid bid){
        repository.save(bid);
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
