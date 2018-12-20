package com.adidas.controller;

import com.adidas.base.ResponseBase;
import com.adidas.constants.Constants;
import com.adidas.entity.UserEntity;
import com.adidas.service.impl.UserEntityImpl;
import com.adidas.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

@Controller
public class UserController {
    @Autowired
    private UserEntityImpl userEntity;
    @GetMapping("login.html")
    public ModelAndView beetl(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @GetMapping("index.html")
    public ModelAndView index(ModelAndView modelAndView, HttpServletRequest request){
        String token=CookieUtil.getUid(request,Constants.TOKEN_MEMBER);
        ResponseBase rb=userEntity.userToken(token);
//        LinkedHashMap loginData = (LinkedHashMap) rb.getData();
//        loginData.get("UserToken");
        UserEntity ue=(UserEntity)rb.getData();
        modelAndView.addObject("user",ue);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
