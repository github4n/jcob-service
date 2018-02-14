package com.aicai.jcob.tjexpert.manager;  

import java.util.List;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.tjexpert.common.domain.TjExpertLevelLog;

/** 
 * @author jing.ming
 * @version 创建时间：2016年1月29日 下午12:14:20 
 * 程序的简单说明 
 */
public interface TjExpertLevelLogManager {

	ModelResult<TjExpertLevelLog> queryLastFormalExpertLog(Long memberId);
	ModelResult<TjExpertLevelLog> queryLastTestExpertLog(Long memberId);
	
	public TjExpertLevelLog queryLastDropInNomalLog(Long memberId);
	
	int insertExpertLevelLog(TjExpertLevelLog tjExpertLevelLog) ;
	
	List<TjExpertLevelLog> quertLevelLogByMemberId(Long memberId);
	
}
 