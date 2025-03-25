package com.pravin.retailer.Service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pravin.retailer.Model.User;
import com.pravin.retailer.Model.UserResponse;
import com.pravin.retailer.Repo.UserRepo;

import jakarta.annotation.PostConstruct;

@Service
public class UserService {
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepo userRepo;

    @Value("${external.api.url}")
    private String url;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<User> fetchAndLoadUsers(){
        try{
            logger.info("Fetching users from external API...");
            ResponseEntity<UserResponse> res = restTemplate.exchange(
                    url,
                    HttpMethod.GET, 
                    null,
                    UserResponse.class 
            );
            if (res.getBody() == null || res.getBody().getUsers().isEmpty()) {
                logger.info("Fetched product detail is null");
                throw new RuntimeException("Failed to fetch users from the external API. Data is empty.");
            }
            userRepo.saveAll(res.getBody().getUsers());
            return res.getBody().getUsers();
        } catch (Exception e) {
            logger.info("Facing a exception while fething the data");
            throw new RuntimeException("Failed to fetch users from the external API.", e);
        }
    }

    @PostConstruct
    public void init(){
         fetchAndLoadUsers();
    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public List<User> findByRole(String role) {
       return userRepo.findByRole(role);
    }

    public List<User> findByAge() {
        List<User> users = userRepo.findAll();
        Collections.sort(users, (user1, user2) -> Integer.compare(user1.getAge(), user2.getAge()));
        return users;
    }

    public User findById(int id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User findBySsn(String ssn) {
        return userRepo.findBySsn(ssn).orElseThrow(() -> new RuntimeException("User not found with ssn: " + ssn));
    }

}
