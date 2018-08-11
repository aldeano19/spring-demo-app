package com.eriel.amex.demo.repository;

import com.eriel.amex.demo.model.User;
import com.eriel.amex.demo.repository.custom.UserRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {
    User findUserByAccountNumber(String accountNumber);
}
