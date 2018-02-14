package com.aicai.jcob.adver.common.service;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.adver.common.domain.ClientTemplate;
import com.aicai.jcob.adver.common.domain.option.ClientTemplateOption;
import com.aicai.jcob.adver.common.result.ClientTemplateData;

import java.util.List;

/**
 * Created by tianlun.wu on 2016/1/30 0030.
 */
public interface ClientTemplateWriteService {

    ModelResult<Boolean> addTemplateAndVersionAndAgentId(ClientTemplateOption option);

    ModelResult<DataPage<ClientTemplate>> queryByPage(DataPage<ClientTemplate> dataPage, ClientTemplateOption option);

    ModelResult<Boolean> deleteById(Long id );

    ModelResult<ClientTemplate> queryById(Long id);

    ModelResult<Boolean> updateByObj(ClientTemplate template);

    /**
     * 前端获取模版内容
     * @param sellClient
     * @param version
     * @param agentId
     * @see com.aicai.jcob.common.constant.PageType
     * @param position
     *
     * @return
     */
    ModelResult<List<ClientTemplateData>> queryContent(Integer sellClient, String version, Integer agentId, Integer position);


}
