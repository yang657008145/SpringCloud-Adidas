package com.adidas.fegin;

import com.adidas.service.PrmoService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
@Component
@FeignClient("yrz")
public interface PrmoFeig extends PrmoService {
}
