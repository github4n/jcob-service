package com.aicai.jcob.adver.manager;


import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.adver.common.domain.ClientTemplate;
import com.aicai.jcob.adver.common.domain.option.ClientTemplateOption;
import com.aicai.jcob.adver.common.result.ClientTemplateData;

import java.util.List;

public interface ClientTemplateManager {
    int insert(ClientTemplate clientTemplate);

    ClientTemplate queryById(long id);

    int deleteById(long id);

    ModelResult<Boolean> deleteTemplateById(long id);

    List<ClientTemplate> queryList(ClientTemplateOption option);

    DataPage<ClientTemplate> queryPage(Long clientType, Integer pageId, DataPage<ClientTemplate> dataPage);

    DataPage<ClientTemplate> queryPage(ClientTemplateOption option, DataPage<ClientTemplate> dataPage);

    ModelResult<Boolean> addTemplateAndVersionAndAgentId (ClientTemplateOption option);

    ModelResult<Boolean> updateTemplate(ClientTemplate template);

    /**
     *  前端调用 获取模版内容
     */
    ModelResult<List<ClientTemplateData>> queryContent(Integer sellClient, String version, Integer agentId, Integer position);

}