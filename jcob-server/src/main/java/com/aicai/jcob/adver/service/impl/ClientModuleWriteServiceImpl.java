package com.aicai.jcob.adver.service.impl;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.adver.common.domain.ClientModule;
import com.aicai.jcob.adver.common.service.ClientModuleWriteService;
import com.aicai.jcob.adver.manager.ClientModuleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tianlun.wu on 2016/1/30 0030.
 */
public class ClientModuleWriteServiceImpl implements ClientModuleWriteService {

    private static final Logger logger = LoggerFactory.getLogger(ClientModuleWriteServiceImpl.class);

    @Resource
    private ClientModuleManager clientModuleManager;


    @Override
    public ModelResult<ClientModule> queryById(long id) {

        return new ModelResult<>(clientModuleManager.queryById(id));
    }

    @Override
    public ModelResult<DataPage<ClientModule>> queryByTemplateId(Integer templateId, DataPage<ClientModule> dataPage) {
        try {
            return new ModelResult<>(clientModuleManager.queryPage(templateId, dataPage));
        } catch (Exception e) {
            logger.error("调用 clientModuleManager.queryPage 报错:{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", "服务异常！找开发人员");
        }
    }

    @Override
    public ModelResult<Boolean> update(ClientModule clientModule) {
        return clientModuleManager.update(clientModule);
    }

    @Override
    public ModelResult<Boolean> addModuleToTemplate(ClientModule clientModule) {
        try {
            int result = clientModuleManager.insert(clientModule);
            if (result == 1) {
                return new ModelResult<>(Boolean.TRUE);
            }
            return new ModelResult<>(Boolean.FALSE);
        } catch (Exception e) {
            logger.error("调用 clientModuleManager.insert 报错:{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public ModelResult<Boolean> updateSort(List<Integer> ids, Integer templateId, String account) {
        return clientModuleManager.saveSort(ids, templateId, account);
    }

    @Override
    public ModelResult<Boolean> deleteByModuleId(Integer moduleId) {
        return clientModuleManager.deleteById((long) moduleId);
    }

    @Override
    public ModelResult<Boolean> updateStatusByModuleId(Integer moduleId, String account) {
        return clientModuleManager.updateStatusByModuleId((long) moduleId, account);
    }
}
