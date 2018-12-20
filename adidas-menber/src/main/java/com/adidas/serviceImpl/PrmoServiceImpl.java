package com.adidas.serviceImpl;

import com.adidas.base.BaseApiService;
import com.adidas.base.ResponseBase;
import com.adidas.dao.PrmoDao;
import com.adidas.entity.Prmo;
import com.adidas.service.PrmoService;
import com.codingapi.tx.annotation.ITxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrmoServiceImpl extends BaseApiService implements PrmoService, ITxTransaction {

    @Autowired
    private PrmoDao md;
    @Override
    public ResponseBase findUserById(@RequestParam("id")int id) {
           Prmo p=md.findUserById(id);
           if(p!=null){
               return setResultSuccess(p);
           }
           return setResultError("没有任何值");
    }
    @Transactional
    @Override
    public ResponseBase inserPrmo(@RequestParam("id") int id,@RequestParam("name") String name) {
       int po=md.inserPrmo(id,name);
       if (po>0){
           return setResultSuccess("增加成功");
       }
        return setResultError("增加失败");
    }
}
