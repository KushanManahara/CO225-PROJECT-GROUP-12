package com.bitmart.bitmartserver.model.initialvalue;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitialValueRepository extends CrudRepository<InitialValue, String> {
}
