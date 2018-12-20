package com.adidas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity {
    private int id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Date created;
    private Date updated;
    private String openid;
}
