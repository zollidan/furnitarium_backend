package com.lefortdesigns.furnitarium_backend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt_secret}")
    private String secret;


    public String generateToken(String username){
        Date exirationDate = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());



        return JWT.create()
                .withSubject("User details")
                .withClaim("username", username)
                .withClaim("aboba", "abobAAAA")
                .withIssuedAt(new Date())
                .withIssuer("LeFort Furnitarium API")
                .withExpiresAt(exirationDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndGetClaim(String token) throws JWTVerificationException {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer("LeFort Furnitarium API")
                .build();

        DecodedJWT jwt = jwtVerifier.verify(token);


        return jwt.getClaim("username").asString();
    }
}
