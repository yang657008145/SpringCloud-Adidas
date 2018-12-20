package com.adidas.serviceimpl;

import com.adidas.fegin.PrmoFeig;
import com.adidas.base.BaseApiService;
import com.adidas.base.ResponseBase;
import com.adidas.dao.NoticeDao;
import com.adidas.entity.Notice;
import com.adidas.service.NoticeService;
import com.codingapi.tx.annotation.TxTransaction;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeServiceImpl extends BaseApiService implements NoticeService {
    @Autowired
    private NoticeDao nd;
    @Autowired
    public PrmoFeig mf;
    @Override
    public ResponseBase noticeId(int id) {
        Notice n = nd.noticeId(id);
        if (n != null) {
            return setResultSuccess(n);
        }
        return setResultError("没有任何信息");
    }
    @TxTransaction(isStart = true)
    @Transactional
    @Override
    public ResponseBase noticeUD(int id,String context) {
        if (StringUtils.isEmpty(context)){
            return setResultError("内容不能空");
        }
        int ud=nd.noticeUd(id,context);
        if(ud>0){
            mf.inserPrmo(5,"111111");
            int i=1/0;
            return setResultSuccess("修改成功");
        }
        return setResultError("修改失败");
    }
}
