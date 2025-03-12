package com.pablo.taskmanager.service.user;

import com.pablo.taskmanager.model.user.User;
import com.pablo.taskmanager.repository.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public User createUser(User user) {
        if (user == null || user.getName() == null || user.getEmail() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("Username and email are required");
        }
        if(user.getName().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Username and password can't be empty");
        }
        try {
                return iUserRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> getUserById(Long id) {
        try {
         return  iUserRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = iUserRepository.findAll();
        if (users.isEmpty()) {
            throw new RuntimeException("No users found in database");
        }
        return users;
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable){
      return iUserRepository.findAll(pageable);
    }

    @Override
    public User updateUser(Long id, User user) {
        Optional<User> existingUser = iUserRepository.findById(id);
        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();

            userToUpdate.setName(user.getName());
            userToUpdate.setEmail(user.getEmail());

            return iUserRepository.save(userToUpdate);
        } else {
            throw new RuntimeException("User with ID " + id + " not found");
        }
    }

    @Override
    public void deleteUser(Long id) {
        if (iUserRepository.existsById(id)) {
            iUserRepository.deleteById(id);
        } else {
            throw new RuntimeException("User with ID " + id + " not found");
        }
    }

}
