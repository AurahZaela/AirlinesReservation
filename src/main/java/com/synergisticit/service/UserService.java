package com.synergisticit.service;
import java.util.List;

import com.synergisticit.domain.User;

public interface UserService {
	User saveUser(User user);
    User getUserById(Long userId);
    List<User> getAllUsers();
    void deleteUserById(Long userId);
    boolean existById(Long userId);
    
    
    User findUserByUsername(String username);
}
