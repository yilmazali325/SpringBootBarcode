package com.example.demo.Controller;

import com.example.demo.Dto.Error;
import com.example.demo.Dto.ResponseInt;
import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping
    public Iterable<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/business")
    public List<User> getBusinessOwners(){
        return userService.getBusinessOwners();
    }

    @GetMapping("/global")
    public List<User> getUsersForGlobal(){
        return userService.getUsersForGlobal();
    }
    @GetMapping("/id")
    public User getUserById(@RequestParam Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/local")
    public List<User> getLocalUsers(@RequestParam String role, @RequestParam String businessName){
        return userService.getLocalUsers(role,businessName);
    }

    @GetMapping("/local/id")
    public User getLocalUserById(@RequestParam long id, @RequestParam String businessName){
        return userService.getLocalUserById(id,businessName);
    }

    @GetMapping("/business/id")
    public User getBusinessUserById(@RequestParam long id){
        return userService.getBusinessUserById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        User userObj ;
        ResponseInt res;
        try{
            userObj = userService.loginUser(user);
        }catch (Exception e){
            res = new Error();
            ((Error) res).setMessage(e.getMessage());
            ((Error) res).setHttpStatus(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(userObj,HttpStatus.OK);
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user){
        String response = userService.saveUser(user);
        return response;
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);

    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam long id){
        userService.deleteUser(id);
    }
}
