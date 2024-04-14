package com.general.loan.repository;

import com.general.loan.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {

    //   Optional<CustomerEntity> findByCustomerId(String customerId);

    List<CustomerEntity> findByUserId(String userId);
}
