package com.adidas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
    private int id;
    private String name;
    private  String content;
    private NoticeType noticetype;
}
