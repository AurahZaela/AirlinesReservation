package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.User;
import com.synergisticit.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
		
		@Autowired UserRepository userRepository;
	    @Autowired BCryptPasswordEncoder bCrypt;
	    
	    @Override
	    public User saveUser(User user) {
	        // use bCrypt to encrypt password while saving
	        String encryptedPassword = bCrypt.encode(user.getPassword());
	        user.setPassword(encryptedPassword);
	        return userRepository.save(user);
	    }

	    @Override
	    public User getUserById(Long userId) {
	        Optional<User> opt = userRepository.findById(userId);
	        if (opt.isPresent()) {
	            return opt.get();
	        }
	        return null;
	    }

	    @Override
	    public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }

	    @Override
	    public void deleteUserById(Long userId) {
	        userRepository.deleteById(userId);
	    }

	    @Override
	    public boolean existById(Long userId) {
	        return userRepository.existsById(userId);
	    }
	    
	    @Override
	    public User findUserByUsername(String username) {
	        return userRepository.findUserByUsername(username);
	    }

}
