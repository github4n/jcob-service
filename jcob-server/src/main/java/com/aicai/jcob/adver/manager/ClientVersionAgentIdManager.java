package com.aicai.jcob.adver.manager;


import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.adver.common.domain.ClientVersionAgentId;
import com.aicai.jcob.adver.common.domain.option.ClientVersionAgentIdOption;
import com.aicai.jcob.adver.common.domain.option.ClientVersionAgentIdVo;

import java.util.List;

public interface ClientVersionAgentIdManager {

    int insert(ClientVersionAgentId clientVersionAgentId);

    ModelResult<Boolean> insertMulti(ClientVersionAgentIdVo clientVersionAgentIdVo);

    ClientVersionAgentId queryById(long id);

    ModelResult<Boolean> deleteById(long id);

    Boolean deleteByBusinessId(Integer businessId);

    DataPage<ClientVersionAgentId> queryByPage(ClientVersionAgentIdOption option, DataPage<ClientVersionAgentId> dataPage);

    /**
     * 根据筛选条件做查询
     * client_type
     * client_version
     * agent_id
     * @param option
     * @return
     */
    List<ClientVersionAgentId> queryListByCondition(ClientVersionAgentIdOption option);

    /**
     * 根据条件查询
     * @param option
     * @return
     */
    List<ClientVersionAgentId> queryList(ClientVersionAgentIdOption option);

    /**
     * 根据条件查询  按照 BusinessId 分组
     * @return
     */
    List<ClientVersionAgentId> queryListGroupByBusinessId(ClientVersionAgentIdOption option);


    /**
     * 删除之前 先判断一下 是否还有筛选条件 不能全部删除
     * @param option
     * @return
     */
    ModelResult<Boolean> deleteById(ClientVersionAgentIdOption option);


}