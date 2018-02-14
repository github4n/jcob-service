package com.aicai.jcob.test.client;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.aicai.jcob.client.common.domain.ClientAppUseLog;
import com.aicai.jcob.client.common.domain.ClientAppUser;
import com.aicai.jcob.client.common.service.ClientAppWriteService;
import com.aicai.jcob.test.TestBase;

/**
 * 
 * 
 * @project jcob-server
 * @author duannp
 * @date 2016年4月12日
 */
public class ClientAppServiceTest  extends TestBase {

    @Autowired
    private ClientAppWriteService clientAppWriteService ;

    @Test
    public void testSaveClientAppUseLog(){
    	
    	clientAppWriteService.saveClientAppUseLog(new ClientAppUseLog("2", 1, 1, "1.1"));
    }
    
    @Test
    public void testSaveClientAppUser(){
    	
    	clientAppWriteService.saveClientAppUser(new ClientAppUser("2", 1L));
    }
    
    

  
}
