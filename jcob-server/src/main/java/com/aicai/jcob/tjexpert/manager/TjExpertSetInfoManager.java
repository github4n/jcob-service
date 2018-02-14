package com.aicai.jcob.tjexpert.manager;  

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.tjexpert.common.domain.constant.TjExpertLevel;
import com.aicai.jcob.tjexpert.common.domain.option.TjExpertSetInfoQueryOption;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertSetAdminResult;

/** 
 * @author jing.ming
 * @version 创建时间：2016年2月3日 上午11:01:25 
 * 程序的简单说明 
 */
public interface TjExpertSetInfoManager {

	DataPage<TjExpertSetAdminResult> queryPage(DataPage<TjExpertSetAdminResult> page,TjExpertSetInfoQueryOption option);
   
	ModelResult<Boolean> insertPersonSetExpert(Long memberId,
			String realName, String certNo, String desc, TjExpertLevel expertLevel,String operaterName);
}
 