package com.aicai.jcob.memberdrawmoney.manager;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.memberdrawmoney.common.domain.MemberDrawMoneyLog;

/**
 * 提款业务事物处理
 * 
 * @project jcob-server
 * @author duannp
 * @date 2016年2月23日
 */
public interface MemberDrawMoneyTranscationManager {
	/**
	 * 提款事物操作
	 * @param memberDrawMoneyLog
	 * @return
	 */
	public ModelResult<MemberDrawMoneyLog> memberDrawMoneyWithTranscation(MemberDrawMoneyLog memberDrawMoneyLog);
}
