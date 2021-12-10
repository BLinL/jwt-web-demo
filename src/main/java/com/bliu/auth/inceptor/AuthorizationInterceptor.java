package com.bliu.auth.inceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if(null == token){
            return false;
        }else{
            token = token.replace("Bearer ", "");
            System.out.println("token " + token);
            if(!verifyToken(token)){
                response.setStatus(403);
                return false;
            }else {
                return true;
            }
        }
    }

    private boolean verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            verifier.verify(token);
        }catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }
}
