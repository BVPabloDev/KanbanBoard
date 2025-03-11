package com.pablo.taskmanager.service.user;


import com.pablo.taskmanager.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUserService {

     User createUser(User user);
     Optional<User> getUserById(Long id);
     List<User> getAllUsers();
     Page<User> getAllUsers(Pageable pageable);
     User updateUser(Long id, User user);
     void deleteUser(Long id);


}
