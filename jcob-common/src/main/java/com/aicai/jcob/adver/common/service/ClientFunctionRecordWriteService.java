package com.aicai.jcob.adver.common.service;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.adver.common.domain.ClientFunctionRecord;
import com.aicai.jcob.adver.common.domain.option.ClientFunctionRecordOption;
import com.aicai.jcob.adver.common.domain.option.ClientVersionAgentIdVo;

import java.util.List;

/**
 * Created by tianlun.wu on 2016/2/25 0025.
 */
public interface ClientFunctionRecordWriteService {

    ModelResult<Boolean> addClientRunRecord(ClientFunctionRecord clientFunctionRecord,ClientVersionAgentIdVo clientVersionAgentId);

    ModelResult<Boolean> update(ClientFunctionRecord clientFunctionRecord);

    ModelResult<Boolean> deleteByIds(List<Integer> ids);

    ModelResult<Boolean> deleteById(Integer id);

    ModelResult<Boolean> updateStatus(Integer id);

    ModelResult<ClientFunctionRecord> queryById(Integer id);

    ModelResult<DataPage<ClientFunctionRecord>> queryByPage(ClientFunctionRecordOption option,
                                                            DataPage<ClientFunctionRecord> dataPage);

    /**
     * 前端查询广告图
     * 默认只查询状态为启用中的广告图
     * @param clientType 客户端类型
     * @param version  版本号
     * @param agentId  渠道号
     * @see com.aicai.jcob.adver.common.constant.AdvertiseLocation
     * @param adLocation  广告图位置
     * @return
     */
    ModelResult<List<ClientFunctionRecord>> queryContent(Integer clientType, String version,
                                                         Integer agentId, Integer adLocation);

}
