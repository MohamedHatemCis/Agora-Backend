package com.training.agora.user;

import com.training.agora.authenticate.RequestAuthenticate;
//import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/agora")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    @CrossOrigin("*")
    public User login(@RequestBody RequestAuthenticate request){
        return userService.login(request);
    }
    @PostMapping("/register")
    @CrossOrigin("*")
    public Boolean saveUser(@RequestBody  User user){
        return userService.saveUser(user);
    }
}
