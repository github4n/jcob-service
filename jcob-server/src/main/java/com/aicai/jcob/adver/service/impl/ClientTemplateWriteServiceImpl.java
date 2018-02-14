package com.aicai.jcob.adver.service.impl;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.adver.common.domain.ClientTemplate;
import com.aicai.jcob.adver.common.domain.option.ClientTemplateOption;
import com.aicai.jcob.adver.common.result.ClientTemplateData;
import com.aicai.jcob.adver.common.service.ClientTemplateWriteService;
import com.aicai.jcob.adver.manager.ClientTemplateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tianlun.wu on 2016/1/30 0030.
 */
public class ClientTemplateWriteServiceImpl implements ClientTemplateWriteService {

    private static final Logger logger = LoggerFactory.getLogger(ClientTemplateWriteServiceImpl.class);

    @Resource
    private ClientTemplateManager clientTemplateManager;

    @Override
    public ModelResult<Boolean> addTemplateAndVersionAndAgentId(ClientTemplateOption option) {

        return clientTemplateManager.addTemplateAndVersionAndAgentId(option);
    }

    @Override
    public ModelResult<DataPage<ClientTemplate>> queryByPage(DataPage<ClientTemplate> dataPage, ClientTemplateOption option) {
        try {
            return new ModelResult<>(clientTemplateManager.queryPage(option, dataPage));
        } catch (Exception e) {
            logger.error("调用 clientTemplateManager.queryPag 报错：{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public ModelResult<Boolean> deleteById(Long id) {
       return clientTemplateManager.deleteTemplateById(id);
    }

    @Override
    public ModelResult<ClientTemplate> queryById(Long id) {
        try {
            return new ModelResult<>(clientTemplateManager.queryById(id));
        } catch (Exception e) {
            logger.error("调用 clientTemplateManager.queryById 报错:{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }

    }

    @Override
    public ModelResult<Boolean> updateByObj(ClientTemplate template) {
        return clientTemplateManager.updateTemplate(template);
    }

    @Override
    public ModelResult<List<ClientTemplateData>> queryContent(Integer sellClient, String version, Integer agentId,Integer position) {
        return clientTemplateManager.queryContent(sellClient, version, agentId, position);
    }
}
