package com.training.agora.user;

import com.training.agora.authenticate.AuthenticationResponse;
import com.training.agora.authenticate.RequestAuthenticate;
//import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/agora")

//Inject the needed classes
@RequiredArgsConstructor

// Permit this url to use these end points
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody RequestAuthenticate request){
        return userService.login(request);
    }
    @PostMapping("/register")
    public AuthenticationResponse saveUser(@RequestBody  User user){
        return userService.saveUser(user);
    }
}
