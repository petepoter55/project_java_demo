package com.example.projectTestDemo.repository;

import com.example.projectTestDemo.entity.ManageUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface UserRepository extends JpaRepository<ManageUser, BigInteger> {
    ManageUser findByUsername(String username);

    List<ManageUser> findByApproved(String status);
}
