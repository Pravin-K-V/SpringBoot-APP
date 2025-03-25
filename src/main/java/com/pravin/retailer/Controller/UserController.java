package com.pravin.retailer.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pravin.retailer.Model.User;
import com.pravin.retailer.Service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/fetch-users")
    public String fetchandLoadUser() {
        userService.fetchAndLoadUsers();
        return "success";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/users")
    public ResponseEntity<List<User>> findAllUsers() {
        try {
            List<User> users = userService.findAllUsers();
            if (users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(users);
            }
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/users/role/{role}")
    public ResponseEntity<List<User>> findByRole(@PathVariable String role) {
        try {
            List<User> users = userService.findByRole(role);
            if (users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(users);
            }
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/users/age")
    public ResponseEntity<List<User>> findByAge() {
        try {
            List<User> users = userService.findByAge();
            if (users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(users);
            }
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/users/search")
    public ResponseEntity<Object> findByIdOrSsn(@RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "ssn", required = false) String ssn) {

        try {
            if (id == null && ssn == null) {
                throw new IllegalArgumentException("Either 'id' or 'ssn' must be provided.");
            }
            User res = null;
            if (id != null) {
                res = userService.findById(id);
            } else if (ssn != null) {
                res = userService.findBySsn(ssn);
            }
            return ResponseEntity.ok(res);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("could not find any user with id or ssn");
        }

    }
}
