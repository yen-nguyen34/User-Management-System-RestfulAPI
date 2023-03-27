package com.ums.restfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ums.restfulapi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
