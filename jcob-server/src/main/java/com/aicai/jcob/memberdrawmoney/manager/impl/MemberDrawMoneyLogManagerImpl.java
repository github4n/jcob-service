package com.aicai.jcob.memberdrawmoney.manager.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericMybatisDao;
import com.aicai.datachange.common.constant.jcob.JcobDataType;
import com.aicai.jcob.datachange.DataChangeFactory;
import com.aicai.jcob.memberdrawmoney.common.domain.MemberDrawMoneyLog;
import com.aicai.jcob.memberdrawmoney.common.domain.constant.AuditStatus;
import com.aicai.jcob.memberdrawmoney.common.domain.constant.DrawMoneyStatus;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyInfoUpdateOption;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyLogQueryOption;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyMannualUpdateOption;
import com.aicai.jcob.memberdrawmoney.common.domain.result.MemberDrawMoneyLogResult;
import com.aicai.jcob.memberdrawmoney.manager.MemberDrawMoneyLogManager;
/**
 * 提款记录管理实现
 * 
 * @project jcob-server
 * @author duannp
 * @date 2016年2月23日
 */
public class MemberDrawMoneyLogManagerImpl implements MemberDrawMoneyLogManager {

	@Autowired
	@Qualifier("memberDbDao")
	private GenericMybatisDao  memberDbDao;
	@Autowired
	private DataChangeFactory dataChangeFactory;
	@Override
	public MemberDrawMoneyLog saveDrawMoneyLog(MemberDrawMoneyLog memberDrawMoneyLog) {
		memberDbDao.insertAndSetupId("MemberDrawMoneyLogMapper.saveDrawMoneyLog", memberDrawMoneyLog);
		return memberDrawMoneyLog;
	}
	@Override
	public MemberDrawMoneyLog queryDrawMoneyLogById(Long id) {
		return memberDbDao.queryUnique("MemberDrawMoneyLogMapper.queryByPrimaryKey", id);
	}
	@Override
	public boolean updateDrawMoneyLogAuditInfo(Long drawMoneyLogId,Integer auditStatus, String auditor, String auditInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id",drawMoneyLogId );
		paramMap.put("auditStatus",auditStatus );
		paramMap.put("auditor", auditor);
		paramMap.put("auditInfo", auditInfo);
		
		if (auditStatus == AuditStatus.audit_unpass.getIndex()) {
			//审核失败，需要把提款状态重置为提款失败
			paramMap.put("status", DrawMoneyStatus.fail.getIndex());
			paramMap.put("drawInfo", AuditStatus.audit_unpass.getDescription());
		}
		boolean ret =  memberDbDao.update("MemberDrawMoneyLogMapper.updateDrawMoneyLogAuditInfo", paramMap) == 1?true:false;
		//发送datachange;
		dataChangeFactory.sendDataChangeForModify(drawMoneyLogId, JcobDataType.jcob_member_draw_money_log
				, null, auditStatus, null, AuditStatus.auditing.getIndex());
		return ret;
	}
	
	@Override
	public DataPage<MemberDrawMoneyLogResult> queryMemberDrawMoneyLogByPage(MemberDrawMoneyLogQueryOption option,
			DataPage<MemberDrawMoneyLogResult> page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("condition", option);
		map.put("startIndex", (page.getPageNo() - 1) * page.getPageSize());
		map.put("pageSize", page.getPageSize());
		return memberDbDao.queryPage("MemberDrawMoneyLogMapper.queryMemberDrawMoneyLogByPageCount",
				"MemberDrawMoneyLogMapper.queryMemberDrawMoneyLogByPage", map, page);
	}
	
	@Override
	public MemberDrawMoneyLogResult statistMemberDrawMoneyLog(MemberDrawMoneyLogQueryOption option) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("condition", option);
		return memberDbDao.queryUnique("MemberDrawMoneyLogMapper.statistMemberDrawMoneyLog", map);
	}
	
	@Override
	public boolean updateDrawMoneyStatus(MemberDrawMoneyInfoUpdateOption updateInfoOption) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id",updateInfoOption.getDrawMoneyLogId() );
		paramMap.put("requestTime", updateInfoOption.getRequestTime());
		paramMap.put("responseTime", updateInfoOption.getResponseTime());
		paramMap.put("newStatus", updateInfoOption.getNewStatus());
		paramMap.put("oldStatus", updateInfoOption.getOldStatus());
		paramMap.put("thirdDrawNo", updateInfoOption.getThirdDrawNo());
		if (StringUtils.isNotBlank(updateInfoOption.getResponseMsg())) {
			paramMap.put("drawInfo", updateInfoOption.getResponseCode()+":"+updateInfoOption.getResponseMsg());
		}
		return memberDbDao.update("MemberDrawMoneyLogMapper.updateDrawMoneyStatus", paramMap) == 1?true:false;
	}
	@Override
	public boolean updateDrawMoneyWithManual(MemberDrawMoneyMannualUpdateOption mannualUpdateOption ) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id",mannualUpdateOption.getDrawMoneyLogId() );
		paramMap.put("requestTime", Calendar.getInstance());
		paramMap.put("responseTime", Calendar.getInstance());
		paramMap.put("updateTime", Calendar.getInstance());
		paramMap.put("status", mannualUpdateOption.getStatus());
		paramMap.put("auditStatus",AuditStatus.audit_pass.getIndex() );
		paramMap.put("auditor", mannualUpdateOption.getOperator());
		paramMap.put("auditInfo", "mannualDrawMoney");
		if (StringUtils.isNotBlank(mannualUpdateOption.getRemark())) {
			paramMap.put("drawInfo", mannualUpdateOption.getRemark());
		}
		return memberDbDao.update("MemberDrawMoneyLogMapper.updateDrawMoneyWithManual", paramMap) == 1?true:false;
	}

}
