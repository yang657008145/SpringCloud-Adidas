package com.adidas.utils;

import java.util.UUID;

import com.adidas.constants.Constants;
import org.springframework.stereotype.Component;

@Component
public class TokenUtils {
    //跟据自己的业务去设定
    public static String getMemberToken() {
        return Constants.TOKEN_MEMBER + "-" + UUID.randomUUID();
    }

    public static String getPayToken() {
        return Constants.TOKEN_PAY + "-" + UUID.randomUUID();
    }
}
