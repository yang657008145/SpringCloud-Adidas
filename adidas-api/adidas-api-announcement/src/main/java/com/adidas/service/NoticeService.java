package com.adidas.service;

import com.adidas.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/notice")
public interface NoticeService {
    @RequestMapping("/noticeId")
    ResponseBase noticeId(int id);
    @RequestMapping("/noticeUD")
    ResponseBase noticeUD(int id,String context);
}
