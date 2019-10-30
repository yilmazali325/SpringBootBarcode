package com.example.demo.Service;

import com.example.demo.Dto.ResponseInt;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void saveUser(User user){ ;
        if(userRepository.findUserByEmail(user.getEmail()) == null) {
            userRepository.save(user);
        }else{
            throw new RuntimeException("User with that email already exists!");
        }
    }

    public User loginUser(User user){
        User userObj = userRepository.findUserByEmail(user.getEmail());
        ResponseInt res;
        if(userObj == null){
            throw new NullPointerException("User not found with this email");
        }
        boolean authenticate = (userObj.getPassword().equals(user.getPassword())) ? true : false;
        if(authenticate){
            return userObj;
        }else {
            throw new RuntimeException("Password is not correct!");
        }
    }

    public User getUserById(long id){
        return userRepository.findById(id).get();
    }
    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }
    public void updateUser(User user){
        if(userRepository.findUserByEmail(user.getEmail()) == null) {
            User userToBeProcessed = userRepository.findById(user.getId()).get();
            userToBeProcessed.setEmail(user.getEmail());
            userToBeProcessed.setPassword(user.getPassword());
            userToBeProcessed.setFirstName(user.getFirstName());
            userToBeProcessed.setLastName(user.getLastName());
            userToBeProcessed.setRole(user.getRole());
            userToBeProcessed.setBusinessBoolean(user.isBusinessBoolean());
            userToBeProcessed.setBusinessName(user.getBusinessName());
            userToBeProcessed.setAccountDisabledStatus(user.isAccountDisabledStatus());
            userRepository.save(userToBeProcessed);
        }else{
            throw new RuntimeException("User with that email already exists!");
        }

    }
    public void deleteUser(long id){
        if(userRepository.findById(id).get() == null){
            throw new RuntimeException("User with given id does not exist!");
        }
        userRepository.deleteById(id);
    }
    public void deleteLocalUser(long id,String role,String businessName)  {
        if(userRepository.findById(id).get() == null){
            throw new RuntimeException("User with given id does not exist!");
        }
        userRepository.deleteUserByIdAndBusinessNameAndRole(id,businessName,role);
    }
    public void deleteBusinessUser(long id,String role){
        if(userRepository.findById(id).get() == null && role.equals("admin")){
            throw new RuntimeException("User with given id does not exist!");
        }
        userRepository.deleteById(id);
    }
    public List<User> getBusinessOwners(){
        return userRepository.findUsersByBusinessBooleanIsTrue();
    }
    public List<User> getUsersForGlobal(){
        return userRepository.findUsersByRole();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).get();
    }
    public List<User> getLocalUsers(String role, String businessName){
        return userRepository.findUsersByBusinessBooleanIsFalseAndRoleAndBusinessName(role,businessName);
    }
    public User getLocalForAdmin(long id, String businessName,String role){
        return userRepository.findUserByIdAndBusinessNameAndRole(id,businessName,role);
    }
    public User getLocalUserById(long id,String role){
        return userRepository.findUsersByIdAndRole(id,role);
    }
    public User getBusinessUserById(long id){
        return userRepository.findUserByIdAndBusinessBooleanIsTrue(id);
    }

}
