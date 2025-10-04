
package com.marble.repos;

import com.marble.entities.Users; 
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {
    
    // Spring Data JPA automatically creates the query for this method
    // It's crucial for the login process.
    Optional<Users> findByEmail(String email);
}