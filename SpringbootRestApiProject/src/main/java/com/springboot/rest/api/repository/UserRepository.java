package com.springboot.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.rest.api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
