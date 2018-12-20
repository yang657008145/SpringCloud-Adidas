package com.adidas.config;

import com.codingapi.tx.config.service.TxManagerTxUrlService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
@Service
public class TxManagerTxUrlServiceImpl implements TxManagerTxUrlService {

    @Value("${tm.manager.url}")
    private String url;

    @Override
    public String getTxUrl() {
        System.out.println("load tm.manager.url ");
        return url;
    }
}
