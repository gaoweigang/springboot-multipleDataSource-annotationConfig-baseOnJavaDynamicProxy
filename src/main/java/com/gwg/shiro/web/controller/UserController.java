package com.gwg.shiro.web.controller;

import com.gwg.shiro.web.service.UserService;
import com.gwg.shiro.web.service.impl.UserServiceImpl;
import com.gwg.shiro.web.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/queryUserVoByUserCode")
    public @ResponseBody  UserVo queryUserVoByUserCode(String userCode){

        UserVo userVo = userService.queryUserVoByUserCode(userCode);
        return userVo;
    }





}
