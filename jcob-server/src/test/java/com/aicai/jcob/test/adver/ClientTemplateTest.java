package com.aicai.jcob.test.adver;

import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.adver.common.constant.UsingStatus;
import com.aicai.jcob.adver.common.domain.ClientTemplate;
import com.aicai.jcob.adver.common.domain.option.ClientTemplateOption;
import com.aicai.jcob.adver.common.result.ClientTemplateData;
import com.aicai.jcob.adver.manager.ClientTemplateManager;
import com.aicai.jcob.common.constant.ClientType;
import com.aicai.jcob.common.constant.PageType;
import com.aicai.jcob.test.TestBase;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tianlun.wu on 2016/3/12 0012.
 */
public class ClientTemplateTest  extends TestBase {


    @Resource
    private ClientTemplateManager clientTemplateManagerImpl;


    /**
     * 前端获取模版
     */
    @Test
    public void testRun(){
        List<ClientTemplateData> result = clientTemplateManagerImpl.queryContent(ClientType.android.getIndex(), "1.0.0", 123456, PageType.firstPage.getIndex()).getModel();
        System.out.println("size:"+result.size());
        for (ClientTemplateData item: result){
            System.out.println("aaa--:" + item.getClientModule().getModuleName());
        }
    }



    @Test
    public void testTemplate(){
        ClientTemplateOption option = new ClientTemplateOption();
        option.setClientType(ClientType.ios.getIndex());
        option.setClientVersion("1.0.0");
        option.setStatus(UsingStatus.using.getIndex());
        DataPage<ClientTemplate> dataPage = new DataPage<>();
        DataPage<ClientTemplate> result = clientTemplateManagerImpl.queryPage(option, dataPage);
        System.out.println(result.getDataList().size());
    }
}
