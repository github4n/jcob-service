package com.aicai.jcob.test.adver;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.adver.common.constant.AdvertiseLocation;
import com.aicai.jcob.adver.common.domain.ClientFunctionRecord;
import com.aicai.jcob.adver.manager.ClientFunctionRecordManager;
import com.aicai.jcob.common.constant.ClientType;
import com.aicai.jcob.test.TestBase;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tianlun.wu on 2016/3/12 0012.
 */
public class ClientFunctionRecordTest extends TestBase {

    @Resource
    private ClientFunctionRecordManager clientFunctionRecordManager ;

    /**
     * 测试前端获取 广告位的接口
     */
    @Test
    public void testClientFun(){
        ModelResult<List<ClientFunctionRecord>> test=  clientFunctionRecordManager.queryList(ClientType.android.getIndex(), "1.0.0", 123456, AdvertiseLocation.HOME_PAGE.getIndex());
        System.out.println(test.getModel().size());
    }


}
