package com.aicai.jcob.test.adver;

import com.aicai.jcob.adver.common.constant.BusinessType;
import com.aicai.jcob.adver.common.domain.ClientVersionAgentId;
import com.aicai.jcob.adver.common.domain.option.ClientVersionAgentIdOption;
import com.aicai.jcob.adver.manager.ClientVersionAgentIdManager;
import com.aicai.jcob.test.TestBase;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tianlun.wu on 2016/3/12 0012.
 */
public class ClientVersionAgentIdTest extends TestBase {


    @Resource
    private ClientVersionAgentIdManager clientVersionAgentIdManagerImpl;

    @Test
    public void testClientVersionAgentId(){
        ClientVersionAgentIdOption option = new ClientVersionAgentIdOption();
        option.setBusinessType(BusinessType.audit.getIndex());
        option.setBusinessId(5);
        List<ClientVersionAgentId> list = clientVersionAgentIdManagerImpl.queryListByCondition(option);
        if(list!=null && !list.isEmpty()){
            for (ClientVersionAgentId item:list){
                if(item.getClientVersion() == null){
                    System.out.println(item.getAgentId());
                }
            }
        }
    }
}
