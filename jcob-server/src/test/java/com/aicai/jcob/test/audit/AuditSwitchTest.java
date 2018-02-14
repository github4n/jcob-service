package com.aicai.jcob.test.audit;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.audit.common.AuditSwitchManager;
import com.aicai.jcob.audit.common.constant.AuditSwitchType;
import com.aicai.jcob.common.constant.ClientType;
import com.aicai.jcob.test.TestBase;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by tianlun.wu on 2016/3/17 0017.
 */
public class AuditSwitchTest  extends TestBase{

    @Resource
    private AuditSwitchManager auditSwitchManager;


    @Test
    public void test(){
        ModelResult<Boolean> result = auditSwitchManager.judge(ClientType.ios.getIndex(), "1.0.0", 123456, AuditSwitchType.buyHuoYan.getIndex());
        System.out.println(result.getModel());
    }


}
