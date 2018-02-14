package com.aicai.jcob.adver.manager;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.adver.common.domain.ClientFunctionRecord;
import com.aicai.jcob.adver.common.domain.option.ClientFunctionRecordOption;
import com.aicai.jcob.adver.common.domain.option.ClientVersionAgentIdVo;

import java.util.List;

/**
 * Created by tianlun.wu on 2016/2/25 0025.
 */
public interface ClientFunctionRecordManager {

    ModelResult<Boolean> insert(ClientFunctionRecord clientFunctionRecord, ClientVersionAgentIdVo clientVersionAgentId);

    ModelResult<Boolean> update(ClientFunctionRecord clientFunctionRecord);

    ModelResult<Boolean> deleteByIds(List<Integer> ids);

    ModelResult<Boolean> deleteById(Integer id);

    ModelResult<Boolean> updateStatus(Integer id);

    ModelResult<ClientFunctionRecord> queryById(Integer id);

    ModelResult<DataPage<ClientFunctionRecord>> queryByPage(ClientFunctionRecordOption option,
                                                            DataPage<ClientFunctionRecord> dataPage);


    ModelResult<List<ClientFunctionRecord>> queryList(Integer clientType, String version,
                                                            Integer agentId, Integer adLocation);
}
