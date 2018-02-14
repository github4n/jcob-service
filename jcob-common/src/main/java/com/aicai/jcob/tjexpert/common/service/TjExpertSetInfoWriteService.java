package com.aicai.jcob.tjexpert.common.service;  

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.tjexpert.common.domain.constant.TjExpertLevel;
import com.aicai.jcob.tjexpert.common.domain.option.TjExpertSetInfoQueryOption;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertSetAdminResult;

/** 
 * @author jing.ming
 * @version 创建时间：2016年2月3日 上午10:56:29 
 * 程序的简单说明 
 */
public interface TjExpertSetInfoWriteService {
	
	PageResult<TjExpertSetAdminResult> pageTjPersonSetExpert(DataPage<TjExpertSetAdminResult> page,TjExpertSetInfoQueryOption option);

	/**
	 * 之前一定是普通用户才能人工设置专家(默认设置为正式专家(不带V))
	 * 会同步更新专家信息
	 * 增加专家升级日志
	 * @param memberId
	 * @param realName
	 * @param certNo
	 * @param desc
	 * @param expertLevel
	 * @param operaterName
	 * @return
	 */
	ModelResult<Boolean> insertPersonSetExpert(Long memberId,
			String realName, String certNo, String desc, TjExpertLevel expertLevel,String operaterName) ;
}
 