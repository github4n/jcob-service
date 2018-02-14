package com.aicai.jcob.audit.service.impl;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.audit.common.AuditSwitchManager;
import com.aicai.jcob.audit.common.constant.AuditSwitchType;
import com.aicai.jcob.audit.common.domain.AuditSwitch;
import com.aicai.jcob.audit.common.domain.option.AuditSwitchOption;
import com.aicai.jcob.audit.common.domain.option.AuditSwitchVo;
import com.aicai.jcob.audit.common.service.AuditSwitchWriteService;
import com.aicai.jcob.common.constant.ClientType;

import javax.annotation.Resource;

/**
 * Created by tianlun.wu on 2016/2/28 0028.
 */
public class AuditSwitchWriteServiceImpl implements AuditSwitchWriteService {

    @Resource
    private AuditSwitchManager auditSwitchManager ;

    @Override
    public ModelResult<Boolean> addAuditSwitch(AuditSwitchVo auditSwitchVo) {
        return auditSwitchManager.insertMulti(auditSwitchVo);
    }

    @Override
    public ModelResult<Boolean> updateStatus(Integer id, String acount) {
        return auditSwitchManager.updateStatusById(id, acount);
    }

    @Override
    public ModelResult<Boolean> update(AuditSwitch auditSwitch) {
        return auditSwitchManager.update(auditSwitch);
    }

    @Override
    public ModelResult<AuditSwitch> queryById(Integer id) {
        return auditSwitchManager.queryById(id);
    }

    @Override
    public ModelResult<Boolean> deleteById(Integer id) {
        return auditSwitchManager.deleteById(id);
    }

    @Override
    public ModelResult<DataPage<AuditSwitch>> queryByPage(AuditSwitchOption option, DataPage<AuditSwitch> dataPage) {
        return auditSwitchManager.queryByPage(option, dataPage);
    }

    @Override
    public ModelResult<Boolean> judge(ClientType clientType, String version, Integer agentId, AuditSwitchType functionId) {

        return auditSwitchManager.judge(clientType.getIndex(), version, agentId, functionId.getIndex());
    }
}
