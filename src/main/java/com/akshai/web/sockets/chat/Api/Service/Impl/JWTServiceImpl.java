package com.akshai.web.sockets.chat.Api.Service.Impl;

import com.akshai.web.sockets.chat.Api.Model.User;
import com.akshai.web.sockets.chat.Api.Service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService {

    @Value("${jwtSecret}")
    private String secret;

    @Override
    public String generateToken(User user) {
        Claims claims= Jwts.claims().setSubject(user.getUsername());
        claims.put("userId",user.getId());
        claims.put("email",user.getEmail());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()*24*60*60*1000))
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        String userName = getUserNameFromToken(token);
        return (userDetails.getUsername().equals(userName) && isTokenExpired(token));
    }

    @Override
    public String getUserNameFromToken(String token) {
        return getClaimFromToken(token,Claims::getSubject);
    }

    @Override
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token,Claims::getExpiration);
    }

    @Override
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    @Override
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    @Override
    public Boolean isTokenExpired(String token) {
        final Date date=getExpirationDateFromToken(token);
        return date.before(new Date());
    }
}
