package com.bitmart.bitmartserver.model.initialvalue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class InitialValueDao {
    @Autowired
    private InitialValueRepository repository;

    public void save(InitialValue value){
        repository.save(value);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public boolean existByID(String ID){
        return repository.existsById(ID);
    }

    public Optional<InitialValue> findByID(String ID){
        return repository.findById(ID);
    }

    public InitialValue getInitValue(String ID){
        List<InitialValue> initialValues = new ArrayList<>();
        InitialValue initialValue= new InitialValue();
        Streamable.of(repository.findAll())
                .forEach(initialValues::add);
        System.out.println("returned all Initial values");
        for (InitialValue i: initialValues) {
            if(Objects.equals(i.getSymbol(), ID)){
                return i;
            }
        }
        return initialValue;
    }
}
