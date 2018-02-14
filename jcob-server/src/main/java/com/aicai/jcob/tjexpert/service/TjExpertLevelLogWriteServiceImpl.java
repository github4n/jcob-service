package com.aicai.jcob.tjexpert.service;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.tjexpert.common.domain.TjExpertLevelLog;
import com.aicai.jcob.tjexpert.common.service.TjExpertLevelLogWriteService;
import com.aicai.jcob.tjexpert.manager.TjExpertLevelLogManager;

/** 
 * @author jing.ming
 * @version 创建时间：2016年1月29日 下午1:32:01 
 * 专家升降机操作日志service
 */
public class TjExpertLevelLogWriteServiceImpl implements TjExpertLevelLogWriteService {

	@Autowired
	@Qualifier("tjExpertLevelLogManagerImpl")
	private TjExpertLevelLogManager tjExpertLevelLogManager ;
	
	@Override
	public ModelResult<TjExpertLevelLog> queryLastFormalExpertLog(Long memberId) {
		return tjExpertLevelLogManager.queryLastFormalExpertLog(memberId);		
	}
	@Override
	public ModelResult<TjExpertLevelLog> queryLastTestExpertLog(Long memberId) {
		return tjExpertLevelLogManager.queryLastTestExpertLog(memberId);		
	}
	
	@Override
	public ModelResult<Boolean> insertExpertLevelLog(TjExpertLevelLog tjExpertLevelLog) {
		ModelResult<Boolean> modelResult = new ModelResult<Boolean>(false);
		int affectCount = tjExpertLevelLogManager.insertExpertLevelLog(tjExpertLevelLog);
		if(affectCount>0){
			modelResult.setModel(true);
		}
		return modelResult;
	}

}
 