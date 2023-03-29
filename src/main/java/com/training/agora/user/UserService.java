package com.training.agora.user;

import com.training.agora.authenticate.RequestAuthenticate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User login(RequestAuthenticate request){
        return checkExists(request);
    }
    public Boolean saveUser(User user){
        user.setCreated_date(new Date());
        User savedUser=userRepository.save(user);
        return savedUser!=null?true:false;
    }

    public User checkExists(RequestAuthenticate request){
        Optional<User>userOptional=userRepository.findUserByUsernameAndPassword(request.getUsername(),request.getPassword());
        return userOptional.isPresent()?userOptional.orElseThrow():null;
    }

    public Optional<User> getUserByUserId(long id){
        return userRepository.findById(id);
    }
}
