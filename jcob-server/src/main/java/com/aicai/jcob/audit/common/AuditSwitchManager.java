package com.aicai.jcob.audit.common;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.audit.common.domain.AuditSwitch;
import com.aicai.jcob.audit.common.domain.option.AuditSwitchOption;
import com.aicai.jcob.audit.common.domain.option.AuditSwitchVo;

import java.util.List;

/**
 * Created by tianlun.wu on 2016/2/28 0028.
 */
public interface AuditSwitchManager {

    ModelResult<Boolean> insertMulti(AuditSwitchVo auditSwitchVo);

    ModelResult<Boolean> insert(AuditSwitch auditSwitch);

    ModelResult<Boolean> update(AuditSwitch auditSwitch);

    ModelResult<DataPage<AuditSwitch>> queryByPage(AuditSwitchOption option, DataPage<AuditSwitch> dataPage);

    ModelResult<Boolean> deleteById(Integer id);

    ModelResult<Boolean> updateStatusById(Integer id,String account);

    ModelResult<AuditSwitch> queryById(Integer id);

    List<AuditSwitch> queryList(AuditSwitchOption option);
    /**
     * 前端调用判断某项功能是否使用
     * @param clientType
     * @param version
     * @param agentId
     *
     * @see com.aicai.jcob.audit.common.constant.AuditSwitchType
     * @param functionId
     * @return
     * false 表示 functionId 所代表的审核功能 关闭
     * true 表示 functionId 所代表的审核功能  开启
     */
    ModelResult<Boolean> judge(Integer clientType, String version, Integer agentId, Integer functionId);
}
