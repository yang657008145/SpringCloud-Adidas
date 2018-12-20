package com.adidas.service.impl;


import com.adidas.base.BaseApiService;
import com.adidas.base.ResponseBase;
import com.adidas.constants.Constants;
import com.adidas.dao.UserEntityDao;
import com.adidas.entity.UserEntity;
import com.adidas.server.UserService;
import com.adidas.utils.*;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class UserEntityImpl extends BaseApiService implements UserService {
        @Autowired
        private MD5Util ml;
        @Autowired
        private UserEntityDao ud;
        @Autowired
        private RedisUtil  ru;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
       public ResponseBase ue(@RequestParam("name") String name, @RequestParam("password")String password,String image,HttpServletResponse response,HttpSession session){
           if(StringUtils.isEmpty(name)){
               return setResultError("不能为空");
         }

         if(StringUtils.isEmpty(password)){
             return  setResultError("密码不能为空、");
         }
         String pw=ml.encode(password);
           UserEntity u=ud.ue(name);
          String s=session.getAttribute("code").toString();
           if(!image.equals(s)){
               return setResultError("验证不一致");
           }
           if(u!=null){
                 if(u.getPassword().equals(pw)){//这里还需要加密+盐
                     u.setPassword("");//返回去不要暴露密码
                     String token= TokenUtils.getMemberToken();//token
                     // ru.setString(token,u.getId(),Constants.TOKEN_MEMBER_TIME);//存redis
                     stringRedisTemplate.opsForValue().set(token,String.valueOf(u.getId()));
                  //   brs.setString(token,u.getId(),Constants.PAY_TOKEN_MEMBER_TIME);
                     //这里还需要存cookie
                     CookieUtil.addCookie(response, Constants.TOKEN_MEMBER,token,Constants.COOKIE_TOKEN_MEMBER_TIME);//设置cookie
                     JSONObject jsonObject=new JSONObject();
                     jsonObject.put("UserToken",token);
                     return setResultSuccess(jsonObject);
                 }
           }
               return setResultError("账号或者密码不正确");
       }
       //图片生成，存redis或者session
    public void verification(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //实例生成验证码对象
        SCaptchaUtil instance = new SCaptchaUtil();
        //将验证码存入session
        System.out.print(instance.getCode());
        session.setAttribute("code",instance.getCode());
        //向页面输出验证码图片
        instance.write(response.getOutputStream());
    }

    @Override
    public ResponseBase userToken(String token) {
        if (StringUtils.isEmpty(token)){
            return setResultError("token过期了");
        }
       int userid=Integer.valueOf(ru.getString(token).toString());
       UserEntity ue=ud.ueId(userid);
       if (ue!=null){
           ue.setPassword("");
           return setResultSuccess(ue);
       }
       return null;
    }
}
