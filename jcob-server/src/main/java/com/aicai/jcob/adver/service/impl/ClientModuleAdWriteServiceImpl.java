package com.aicai.jcob.adver.service.impl;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.adver.common.domain.ClientModuleAd;
import com.aicai.jcob.adver.common.domain.option.ClientModuleAdOption;
import com.aicai.jcob.adver.common.service.ClientModuleAdWriteService;
import com.aicai.jcob.adver.manager.ClientModuleAdManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Created by tianlun.wu on 2016/1/30 0030.
 */
public class ClientModuleAdWriteServiceImpl implements ClientModuleAdWriteService {

    private static Logger logger = LoggerFactory.getLogger(ClientModuleAdWriteServiceImpl.class);

    @Resource
    private ClientModuleAdManager clientModuleAdManager;


    @Override
    public ModelResult<DataPage<ClientModuleAd>> queryPage(ClientModuleAdOption option, DataPage<ClientModuleAd> dataPage) {
        try {
            return new ModelResult<>(clientModuleAdManager.queryPage(option, dataPage));
        } catch (Exception e) {
            logger.error("调用 clientModuleAdManager.queryPage 报错:{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public ModelResult<Boolean> addModuleAd(ClientModuleAd clientModuleAd) {
        return clientModuleAdManager.insert(clientModuleAd);
    }

    @Override
    public ModelResult<Boolean> deleteById(Integer id) {
        return clientModuleAdManager.deleteById((long) id);
    }

    @Override
    public ModelResult<Boolean> updateModelAdStatus(Integer id, String account) {
        return clientModuleAdManager.updateModuleAdStatus(id, account);
    }

    @Override
    public ModelResult<ClientModuleAd> queryById(Integer id) {
        try {
            return new ModelResult<>(clientModuleAdManager.queryById(id));
        } catch (Exception e) {
            logger.error("调用 clientModuleAdManager.queryById 报错:{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public ModelResult<Boolean> update(ClientModuleAd clientModuleAd) {
        return clientModuleAdManager.updateModuleAd(clientModuleAd);
    }
}
