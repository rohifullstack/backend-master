package com.general.loan.repository;

import com.general.loan.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

    public Optional<UserEntity> findByUserIdAndUserPasswordAndStatus(String userId, String userPassword, String status);

    public Optional<UserEntity> findByUserIdAndStatus(String userId, String status);

    public List<UserEntity> findAllByUserTypeAndStatus(UserEntity.UserType userType, String status);



}
