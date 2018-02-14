package com.aicai.jcob.test.client;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.client.common.domain.ClientUpgrade;
import com.aicai.jcob.client.manager.ClientUpgradeManager;
import com.aicai.jcob.common.constant.ClientType;
import com.aicai.jcob.test.TestBase;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tianlun.wu on 2016/3/12 0012.
 */
public class ClientUpgradeTest  extends TestBase {

    @Resource
    private ClientUpgradeManager clientUpgradeManager ;

    @Test
    public void testUpgrade(){

        ModelResult<ClientUpgrade> result = clientUpgradeManager.judgeNeedUpgrade("1.0.0", ClientType.android, 123456L);

        System.out.println(result.getModel() == null);

    }

    @Test
    public void testClient(){
        List<String> stringList = clientUpgradeManager.queryAllVersion(ClientType.android.getIndex());
        System.out.println(stringList);
    }

    @Test
    public void testRecentVersion(){
        System.out.println(clientUpgradeManager.queryRecentVersion(ClientType.android, 2335037L).getModel().getCurVersion());
    }
}
