package com.aicai.jcob.tjexpert.manager.impl;  

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.dao.GenericMybatisDao;
import com.aicai.jcob.tjexpert.common.domain.TjExpertLevelLog;
import com.aicai.jcob.tjexpert.manager.TjExpertLevelLogManager;

/** 
 * @author jing.ming
 * @version 创建时间：2016年1月29日 下午12:15:00 
 * 程序的简单说明 
 */
public class TjExpertLevelLogManagerImpl implements TjExpertLevelLogManager {

	@Autowired
	@Qualifier("tjplanDbDao")
	private GenericMybatisDao dao;
	
	@Override
	public ModelResult<TjExpertLevelLog> queryLastFormalExpertLog(Long memberId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("memberId", memberId) ;
		List<TjExpertLevelLog> tjExpertLevelLogList  = dao.
				<TjExpertLevelLog>queryList("TjExpertLevelLogMapper.queryFormalExpertLog", map);
		if(tjExpertLevelLogList==null||tjExpertLevelLogList.size()==0){
			return new ModelResult<TjExpertLevelLog>();
		}
		return new ModelResult<TjExpertLevelLog>(tjExpertLevelLogList.get(0));
	}

	@Override
	public ModelResult<TjExpertLevelLog> queryLastTestExpertLog(Long memberId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("memberId", memberId) ;
		List<TjExpertLevelLog> tjExpertLevelLogList = dao.
				<TjExpertLevelLog>queryList("TjExpertLevelLogMapper.queryTestExpertLog", map);	
		if(tjExpertLevelLogList==null||tjExpertLevelLogList.size()==0){
			return new ModelResult<TjExpertLevelLog>();
		}
		return new ModelResult<TjExpertLevelLog>(tjExpertLevelLogList.get(0));
	}
	@Override
	public TjExpertLevelLog queryLastDropInNomalLog(Long memberId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("memberId", memberId) ;
		List<TjExpertLevelLog> tjExpertLevelLogList = dao.
				<TjExpertLevelLog>queryList("TjExpertLevelLogMapper.queryDropInNomalLog", map);	
		if(tjExpertLevelLogList==null||tjExpertLevelLogList.size()==0){
			return null;
		}
		return tjExpertLevelLogList.get(0);
	}
	@Override
	public int insertExpertLevelLog(TjExpertLevelLog tjExpertLevelLog) {
		tjExpertLevelLog.setCreateTime(new Date());
		return dao.insertAndSetupId("TjExpertLevelLogMapper.insertExpertLevelLog", tjExpertLevelLog);
	}

	@Override
	public List<TjExpertLevelLog> quertLevelLogByMemberId(Long memberId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("memberId", memberId) ;
		List<TjExpertLevelLog> tjExpertLevelLogList  = dao.
				<TjExpertLevelLog>queryList("TjExpertLevelLogMapper.queryLevelLogBymemberId", map);
		if(tjExpertLevelLogList==null||tjExpertLevelLogList.size()==0){
			return null ;
		}
		return tjExpertLevelLogList;
	}

}
 