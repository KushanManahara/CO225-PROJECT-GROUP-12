package com.bitmart.bitmartserver.model.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultDao {
    @Autowired
    private ResultRepository repository;

    public void save(Result result){
        repository.save(result);
    }
}
