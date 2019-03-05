package com.clickbait.orient.database;

import com.clickbait.orient.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * User repository to CRUD users
 */
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

    boolean existsByEmail(String email);
}
