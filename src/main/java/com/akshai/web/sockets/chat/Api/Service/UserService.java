package com.akshai.web.sockets.chat.Api.Service;

import com.akshai.web.sockets.chat.Api.Exception.UserAlreadyExists;
import com.akshai.web.sockets.chat.Api.Exception.UserNameNotFoundException;
import com.akshai.web.sockets.chat.Api.Model.User;
import com.akshai.web.sockets.chat.Api.dto.LoginDTO;
import com.akshai.web.sockets.chat.Api.dto.RegistrationDTO;
import com.akshai.web.sockets.chat.Api.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDTO registerUser(RegistrationDTO register) throws UserAlreadyExists;
    String  login(LoginDTO loginDTO) throws UserNameNotFoundException;
}
