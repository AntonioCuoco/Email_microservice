package com.devBlog.email.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.devBlog.email.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
	
}
