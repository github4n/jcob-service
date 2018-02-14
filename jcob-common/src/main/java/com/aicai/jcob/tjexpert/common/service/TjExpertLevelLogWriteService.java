package com.aicai.jcob.tjexpert.common.service;  

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.tjexpert.common.domain.TjExpertLevelLog;

/** 
 * @author jing.ming
 * @version 创建时间：2016年1月29日 下午12:20:42 
 * 程序的简单说明 
 */
public interface TjExpertLevelLogWriteService {
	
	ModelResult<TjExpertLevelLog> queryLastFormalExpertLog(Long memberId);
	
	ModelResult<TjExpertLevelLog> queryLastTestExpertLog(Long memberId);
	
	ModelResult<Boolean> insertExpertLevelLog(TjExpertLevelLog tjExpertLevelLog);
}
 