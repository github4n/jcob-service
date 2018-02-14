package com.aicai.jcob.adver.manager;


import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.adver.common.domain.ClientModuleAd;
import com.aicai.jcob.adver.common.domain.option.ClientModuleAdOption;

import java.util.List;

public interface ClientModuleAdManager {

    ModelResult<Boolean> insert(ClientModuleAd clientModuleAd);

    ClientModuleAd queryById(long id);

    ModelResult<Boolean> deleteById(long id);

    ModelResult<Boolean> updateModuleAdStatus(Integer id, String account);

    ModelResult<Boolean> updateModuleAd(ClientModuleAd clientModuleAd);

    ClientModuleAd queryByModuleIdAndModulePosition(Integer moduleId, Integer postion);

    List<ClientModuleAd> queryList(ClientModuleAdOption option);

    DataPage<ClientModuleAd> queryPage(ClientModuleAdOption option, DataPage<ClientModuleAd> dataPage);

    /**
     * 将模版的状态为使用中的时候
     * 判断一下该模版中是否存在有效的 moduleAd
     * @param templateId
     * @return
     */
    Integer isExsitAvailableModuleAd(long templateId);
}