package com.akshai.web.sockets.chat.Api.Service.Impl;


import com.akshai.web.sockets.chat.Api.Exception.UserAlreadyExists;
import com.akshai.web.sockets.chat.Api.Exception.UserNameNotFoundException;
import com.akshai.web.sockets.chat.Api.Mapper.UserMapper;
import com.akshai.web.sockets.chat.Api.Model.User;
import com.akshai.web.sockets.chat.Api.Repository.UserRepository;
import com.akshai.web.sockets.chat.Api.Service.UserService;
import com.akshai.web.sockets.chat.Api.dto.LoginDTO;
import com.akshai.web.sockets.chat.Api.dto.RegistrationDTO;
import com.akshai.web.sockets.chat.Api.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final JWTServiceImpl jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO registerUser(RegistrationDTO register) throws UserAlreadyExists {
        if(userRepository.findByUsername(register.getUsername()).isPresent() ||
                userRepository.findByEmail(register.getEmail()).isPresent() ) {
            throw new UserAlreadyExists();
        }
        User user=new User();
        user.setUsername(register.getUsername());
        user.setEmail(register.getEmail());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        return userMapper.toDTO(userRepository.save(user));
    }

    public User findUserByUsername(String username) throws UserNameNotFoundException {
        Optional<User> opUser = userRepository.findByUsername(username);
        if(opUser.isEmpty()) {
            throw new UserNameNotFoundException();
        }
        return opUser.get();
    }

    @Override
    public String login(LoginDTO loginDTO) throws UserNameNotFoundException {
        User user=findUserByUsername(loginDTO.getUsername());
        if(!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())){
            throw new UserNameNotFoundException();
        }
        String jwt = jwtService.generateToken(user);
        return "";
    }


    @Override
    public UserDetails loadUserByUsername(String username)  {
        User user= null;
        try {
            user = findUserByUsername(username);
        } catch (UserNameNotFoundException e) {
            throw new RuntimeException(e);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }
}
