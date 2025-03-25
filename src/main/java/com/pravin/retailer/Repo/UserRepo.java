package com.pravin.retailer.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pravin.retailer.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    List<User> findByRole(String role);

    Optional<User> findBySsn(String ssn);

}
