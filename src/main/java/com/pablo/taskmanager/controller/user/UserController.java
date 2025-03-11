package com.pablo.taskmanager.controller.user;
import com.pablo.taskmanager.model.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return Arrays.asList(
                new User(1L, "Pablo", "pablo@mail.com", "password"),
                new User(2L, "Anja", "Anja@mail.com", "password")
        );
    }

}
