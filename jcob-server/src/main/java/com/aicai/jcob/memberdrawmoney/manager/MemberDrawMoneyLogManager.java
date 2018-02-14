package com.aicai.jcob.memberdrawmoney.manager;

import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.memberdrawmoney.common.domain.MemberDrawMoneyLog;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyInfoUpdateOption;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyLogQueryOption;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyMannualUpdateOption;
import com.aicai.jcob.memberdrawmoney.common.domain.result.MemberDrawMoneyLogResult;
/**
 * 提款记录管理
 * @project jcob-server
 * @author duannp
 * @date 2016年2月23日
 */
public interface MemberDrawMoneyLogManager {
	/**
	 * 保存提款记录
	 * @param memberDrawMoneyLog
	 * @return
	 */
	public MemberDrawMoneyLog saveDrawMoneyLog(MemberDrawMoneyLog memberDrawMoneyLog);
	
	/**
	 * 查询提交记录
	 * @param drawMoneyLogId
	 * @return
	 */
	public MemberDrawMoneyLog queryDrawMoneyLogById(Long drawMoneyLogId);
	/**
	 * 修改审核信息
	 * @param drawMoneyLogId
	 * 提款流水id
	 * @param auditStatus
	 * 审核状态
	 * @param auditor
	 * 审核人
	 * @param remark
	 * 可null ,记录审核失败内容
	 * @return
	 */
	public boolean updateDrawMoneyLogAuditInfo(Long drawMoneyLogId,Integer auditStatus,String auditor,String remark);
	
	
	/**
	 * 分页查询用户提款方式
	 * 
	 * @param option
	 * @param page
	 * @return
	 */
	public DataPage<MemberDrawMoneyLogResult> queryMemberDrawMoneyLogByPage(MemberDrawMoneyLogQueryOption option,
			DataPage<MemberDrawMoneyLogResult> page);
	
	/**
	 * 统计用户提款
	 * 
	 * @param option
	 * @return
	 */
	public MemberDrawMoneyLogResult statistMemberDrawMoneyLog(MemberDrawMoneyLogQueryOption option);
	
	/**
	 * 提款状态相关信息修改
	 * @param updateInfoOption
	 * @return
	 */
	public boolean updateDrawMoneyStatus(MemberDrawMoneyInfoUpdateOption updateInfoOption );
	
	/**
	 * 人工下发，修改提款信息
	 * @param updateInfoOption
	 * @return
	 */
	public boolean updateDrawMoneyWithManual(MemberDrawMoneyMannualUpdateOption mannualUpdateOption );
}
