package com.adidas.server;
import com.adidas.base.ResponseBase;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public interface UserService {
    ResponseBase ue(String name,String password, String image, HttpServletResponse response, HttpSession session);
    void verification(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException;
    ResponseBase userToken(String token);
}
