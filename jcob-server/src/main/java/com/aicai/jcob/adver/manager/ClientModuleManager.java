package com.aicai.jcob.adver.manager;


import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.adver.common.domain.ClientModule;

import java.util.List;

public interface ClientModuleManager {

    int insert(ClientModule clientModule);

    ClientModule queryById(long id);

    ModelResult<Boolean> update(ClientModule clientModule);

    int countModuleByTemplateId(Integer moduleStyleId);

    ModelResult<Boolean> deleteById(long id);

    ModelResult<Boolean> updateStatusByModuleId(long id, String account);

    List<ClientModule> queryList(long templateId, int status);

    List<ClientModule> queryListUnionClientTemplate(long templateId, int status, Integer position);

    List<ClientModule> queryList(long templateId);

    DataPage<ClientModule> queryPage(long templateId, DataPage<ClientModule> dataPage);

    ModelResult<Boolean> saveSort(List<Integer> ids,Integer templateId,String account);

}