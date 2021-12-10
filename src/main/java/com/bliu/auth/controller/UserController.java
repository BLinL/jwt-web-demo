package com.bliu.auth.controller;

import com.bliu.auth.base.ResultData;
import com.bliu.auth.pojo.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/user")
@RestController
public class UserController {

    @PostMapping("/update")
    public ResultData<?> updateUser(@RequestBody User user){
        return ResultData.success();
    }
}
