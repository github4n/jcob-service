package com.aicai.jcob.adver.service.impl;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.adver.common.domain.ClientVersionAgentId;
import com.aicai.jcob.adver.common.domain.option.ClientVersionAgentIdOption;
import com.aicai.jcob.adver.common.domain.option.ClientVersionAgentIdVo;
import com.aicai.jcob.adver.common.service.ClientVersionAgentIdWriteService;
import com.aicai.jcob.adver.manager.ClientVersionAgentIdManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tianlun.wu on 2016/1/30 0030.
 */
public class ClientVersionAgentIdWriteServiceImpl implements ClientVersionAgentIdWriteService {

    private static final Logger logger = LoggerFactory.getLogger(ClientVersionAgentIdWriteServiceImpl.class);

    @Resource
    private ClientVersionAgentIdManager clientVersionAgentIdManager;

    @Override
    public ModelResult<Boolean> addClientVersionAndAgentId(ClientVersionAgentIdVo clientVersionAgentId) {

        return clientVersionAgentIdManager.insertMulti(clientVersionAgentId);
    }

    @Override
    public ModelResult<List<ClientVersionAgentId>> queryList(ClientVersionAgentIdOption option) {
        try {
            return new ModelResult<>(clientVersionAgentIdManager.queryList(option));
        } catch (Exception e) {
            logger.error("clientVersionAgentIdManager.queryList 报错:{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public ModelResult<List<ClientVersionAgentId>> queryListByCondition(ClientVersionAgentIdOption option) {
        try {
            return new ModelResult<>(clientVersionAgentIdManager.queryList(option));
        } catch (Exception e) {
            logger.error("clientVersionAgentIdManager.queryList 报错:{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public ModelResult<Boolean> deleteClientTypeAndAgentId(Integer id) {

        return clientVersionAgentIdManager.deleteById((long) id);
    }

    @Override
    public ModelResult<Boolean> deleteClientTypeAndAgentId(ClientVersionAgentIdOption option) {
        return clientVersionAgentIdManager.deleteById(option);
    }
}
