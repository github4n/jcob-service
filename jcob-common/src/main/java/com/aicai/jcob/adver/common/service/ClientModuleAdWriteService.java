package com.aicai.jcob.adver.common.service;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.adver.common.domain.ClientModuleAd;
import com.aicai.jcob.adver.common.domain.option.ClientModuleAdOption;

/**
 * Created by tianlun.wu on 2016/1/30 0030.
 */
public interface ClientModuleAdWriteService {

    ModelResult<DataPage<ClientModuleAd>> queryPage(ClientModuleAdOption option, DataPage<ClientModuleAd> dataPage);

    ModelResult<Boolean> addModuleAd(ClientModuleAd clientModuleAd);

    ModelResult<Boolean> deleteById(Integer id );

    ModelResult<Boolean> updateModelAdStatus(Integer id, String account);

    ModelResult<Boolean> update(ClientModuleAd clientModuleAd);

    ModelResult<ClientModuleAd> queryById(Integer id);

}
