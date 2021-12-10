package com.bliu.auth.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bliu.auth.Tools.BCrypt;
import com.bliu.auth.base.ResultData;
import com.bliu.auth.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/user")
public class LoginController {

    @GetMapping("/login")
    @ResponseBody
    public String login(String loginAccount, String password) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = foundUser(loginAccount);
        if(user == null) {
           return objectMapper.writeValueAsString(ResultData.failWithData("不存在此用户", null));
        }else {
            boolean checked = checkPass(password, user.getPassword());
            if(!checked) {
                return objectMapper.writeValueAsString(ResultData.failWithData("用户名或密码错误", null));
            }else {
                Algorithm algorithm = Algorithm.HMAC256("secret");
                String token =  JWT.create()
                        .withClaim("name", user.getName())
                        .withClaim("expireAt", System.currentTimeMillis() + 10 * 60 * 1000)
                        .sign(algorithm);

                return objectMapper.writeValueAsString(ResultData.failWithData("登录成功", token));
            }
        }
    }

    private boolean checkPass(String password, String password1) {
        return BCrypt.checkpw(password, password1);
    }

    public User foundUser(String loginAccount){
        return new User(1, "caiz", "$2a$12$Q9u2cjWej5LHPkTaFWHo9eXFz.7X9NDvq9F8Nt0dvYQYCasPNgafG", "zhsan", "aa@qq.com");
    }

//    public static void main(String[] args) {
//        System.out.println(BCrypt.hashpw("123", BCrypt.gensalt(12)));
//    }
}
