package com.adidas.controller;

import com.adidas.base.ResponseBase;
import com.adidas.service.impl.UserEntityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    private UserEntityImpl uei;
    @PostMapping("/userQuery")
    public ResponseBase ue(@RequestParam("name") String name, @RequestParam("password")String password, String image, HttpServletResponse response, HttpSession session){
      return   uei.ue(name,password,image,response,session);
    }
    @RequestMapping("/verification")
    public void verification(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        uei.verification(request,response,session);
    }
}
