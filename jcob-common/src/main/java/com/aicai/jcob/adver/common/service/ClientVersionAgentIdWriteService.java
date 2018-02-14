package com.aicai.jcob.adver.common.service;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.adver.common.domain.ClientVersionAgentId;
import com.aicai.jcob.adver.common.domain.option.ClientVersionAgentIdOption;
import com.aicai.jcob.adver.common.domain.option.ClientVersionAgentIdVo;

import java.util.List;

/**
 * Created by tianlun.wu on 2016/1/30 0030.
 */
public interface ClientVersionAgentIdWriteService {

    ModelResult<Boolean> addClientVersionAndAgentId(ClientVersionAgentIdVo clientVersionAgentId);

    ModelResult<List<ClientVersionAgentId>> queryList(ClientVersionAgentIdOption option);

    ModelResult<List<ClientVersionAgentId>> queryListByCondition(ClientVersionAgentIdOption option);

    ModelResult<Boolean> deleteClientTypeAndAgentId(Integer id );

    ModelResult<Boolean> deleteClientTypeAndAgentId(ClientVersionAgentIdOption option);
}
