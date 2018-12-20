package com.adidas.service;

import com.adidas.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/prmo")
public interface PrmoService {
    @RequestMapping("/findUserById")
    ResponseBase findUserById(int id);
    @RequestMapping("/inserPrmo")
    ResponseBase inserPrmo(@RequestParam("id")int id, @RequestParam("name")String name);
}
