package com.training.agora.user;

import com.training.agora.authenticate.AuthenticationResponse;
import com.training.agora.authenticate.JwtService;
import com.training.agora.authenticate.RequestAuthenticate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    //Login the user if exists and generate a token from his info then return the token
    public AuthenticationResponse login(RequestAuthenticate request){
        User user= checkExists(request);
        if(user!=null)
        {
            var token=jwtService.generateToken(user);
            return AuthenticationResponse.builder().token(token).build();
        }
        else
        {
            throw new RuntimeException("InValid username or password");
        }
    }
    //Save the user in the database then generate a token then return it
    public AuthenticationResponse saveUser(User user){
        try {
            user.setCreated_date(new Date());
            //Check if there is a user with the same username or not
            if(!checkExistedUsername(user.getUsername())){
                User savedUser=userRepository.save(user);
                //generate a token
                var token=jwtService.generateToken(savedUser);
                return AuthenticationResponse.builder().token(token).build();
            }
            else
                return null;
        }catch (Exception ex)
        {
            return null;
        }
    }
    //Check if there is a user with this username or not
    public Boolean checkExistedUsername(String username)
    {
        Optional<User>optionalUser=userRepository.findUserByUsername(username);
        return optionalUser.isPresent()?true:false;
    }
    //Check if there is any exist user with this username and password or not
    public User checkExists(RequestAuthenticate request){
        Optional<User>userOptional=userRepository.findUserByUsernameAndPassword(request.getUsername(),request.getPassword());
        return userOptional.isPresent()?userOptional.orElseThrow():null;
    }

    //Get the user details with the id
    public Optional<User> getUserByUserId(long id){
        return userRepository.findById(id);
    }
}
