package com.aicai.jcob.adver.common.service;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.adver.common.domain.ClientModule;

import java.util.List;

/**
 * Created by tianlun.wu on 2016/1/30 0030.
 */
public interface ClientModuleWriteService {


    ModelResult<ClientModule> queryById(long id);

    ModelResult<DataPage<ClientModule>> queryByTemplateId(Integer templateId, DataPage<ClientModule> dataPage);


    ModelResult<Boolean> update (ClientModule clientModule);

    ModelResult<Boolean> addModuleToTemplate(ClientModule clientModule);

    ModelResult<Boolean> updateSort(List<Integer> ids , Integer templateId, String account );

    ModelResult<Boolean> deleteByModuleId(Integer moduleId);

    ModelResult<Boolean> updateStatusByModuleId(Integer moduleId, String account);
}
