package com.aicai.jcob.audit.common.service;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.audit.common.constant.AuditSwitchType;
import com.aicai.jcob.audit.common.domain.AuditSwitch;
import com.aicai.jcob.audit.common.domain.option.AuditSwitchOption;
import com.aicai.jcob.audit.common.domain.option.AuditSwitchVo;
import com.aicai.jcob.common.constant.ClientType;

/**
 * Created by tianlun.wu on 2016/2/28 0028.
 */
public interface AuditSwitchWriteService {

    ModelResult<Boolean> addAuditSwitch(AuditSwitchVo auditSwitchVo);

    ModelResult<Boolean> updateStatus(Integer id, String acount);

    ModelResult<Boolean> update(AuditSwitch auditSwitch);

    ModelResult<AuditSwitch> queryById(Integer id);

    ModelResult<Boolean> deleteById(Integer id);

    ModelResult<DataPage<AuditSwitch>> queryByPage(AuditSwitchOption option, DataPage<AuditSwitch> dataPage);

    /**
     * @see com.aicai.jcob.common.constant.ClientType
     * @param clientType
     * @param version  版本号
     * @param agentId  渠道号
     * @see com.aicai.jcob.audit.common.constant.AuditSwitchType
     * @param functionId
     * @return
     * false 表示 functionId 所代表的审核功能 关闭
     * true 表示 functionId 所代表的审核功能  开启
     */
    ModelResult<Boolean> judge(ClientType clientType, String version, Integer agentId, AuditSwitchType functionId);
}
