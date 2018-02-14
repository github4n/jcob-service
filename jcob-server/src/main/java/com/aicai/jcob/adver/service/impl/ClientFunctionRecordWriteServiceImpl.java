package com.aicai.jcob.adver.service.impl;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.adver.common.domain.ClientFunctionRecord;
import com.aicai.jcob.adver.common.domain.option.ClientFunctionRecordOption;
import com.aicai.jcob.adver.common.domain.option.ClientVersionAgentIdVo;
import com.aicai.jcob.adver.common.service.ClientFunctionRecordWriteService;
import com.aicai.jcob.adver.manager.ClientFunctionRecordManager;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tianlun.wu on 2016/2/25 0025.
 */
public class ClientFunctionRecordWriteServiceImpl implements ClientFunctionRecordWriteService {

    @Resource
    private ClientFunctionRecordManager clientFunctionRecordManager ;

    @Override
    public ModelResult<Boolean> addClientRunRecord(ClientFunctionRecord clientFunctionRecord,ClientVersionAgentIdVo clientVersionAgentId) {
        return clientFunctionRecordManager.insert(clientFunctionRecord, clientVersionAgentId);
    }

    @Override
    public ModelResult<Boolean> update(ClientFunctionRecord clientFunctionRecord) {
        return clientFunctionRecordManager.update(clientFunctionRecord);
    }

    @Override
    public ModelResult<Boolean> deleteByIds(List<Integer> ids) {
        return clientFunctionRecordManager.deleteByIds(ids);
    }

    @Override
    public ModelResult<Boolean> updateStatus(Integer id) {
        return clientFunctionRecordManager.updateStatus(id);
    }

    @Override
    public ModelResult<ClientFunctionRecord> queryById(Integer id) {
        return clientFunctionRecordManager.queryById(id);
    }

    @Override
    public ModelResult<DataPage<ClientFunctionRecord>> queryByPage(ClientFunctionRecordOption option, DataPage<ClientFunctionRecord> dataPage) {
        return clientFunctionRecordManager.queryByPage(option, dataPage);
    }

    @Override
    public ModelResult<List<ClientFunctionRecord>> queryContent(Integer clientType, String version, Integer agentId, Integer adLocation) {
        return clientFunctionRecordManager.queryList(clientType, version, agentId, adLocation);
    }

    @Override
    public ModelResult<Boolean> deleteById(Integer id) {
        return clientFunctionRecordManager.deleteById(id);
    }
}
