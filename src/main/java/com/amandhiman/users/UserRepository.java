package com.amandhiman.users;

import org.springframework.data.repository.CrudRepository;

import com.amandhiman.users.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}