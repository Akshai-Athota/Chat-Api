package com.akshai.web.sockets.chat.Api.Service;

import com.akshai.web.sockets.chat.Api.Model.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

public interface JWTService {
    String generateToken(User user);
    Boolean validateToken(String token, UserDetails userDetails);
    String getUserNameFromToken(String token);
    Date getExpirationDateFromToken(String token);
    <T> T getClaimFromToken(String toke, Function<Claims,T> claimResolver);
    Claims getAllClaimsFromToken(String token);
    Boolean isTokenExpired(String token);
}
