package com.example.demo.Repository;

import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByEmail(String email);
    List<User> findUsersByBusinessBooleanIsTrue();
    List<User> findUsersByBusinessBooleanIsFalseAndRoleAndBusinessName(String role, String businessName);
    @Query("SELECT t FROM User t WHERE t.role='local'")
    List<User> findUsersByRole();
    User findUserByIdAndBusinessBooleanIsTrue(long id);
    User findUserByIdAndBusinessBooleanIsFalseAndBusinessName(long id, String businessName);
}