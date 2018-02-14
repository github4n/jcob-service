package com.aicai.jcob.memberdrawmoney.manager.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericMybatisDao;
import com.aicai.jcob.common.utils.DateUtil;
import com.aicai.jcob.memberdrawmoney.common.domain.MemberDrawMoneyWay;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyWayQueryOption;
import com.aicai.jcob.memberdrawmoney.common.domain.result.MemberDrawMoneyWayResult;
import com.aicai.jcob.memberdrawmoney.manager.MemberDrawMoneyWayManager;
/**
 * 提款方式管理实现
 * 
 * @project jcob-server
 * @author duannp
 * @date 2016年2月23日
 */
public class MemberDrawMoneyWayManagerImpl implements MemberDrawMoneyWayManager {
	@Autowired
	@Qualifier("memberDbDao")
	private GenericMybatisDao  memberDbDao;
	@Override
	public List<MemberDrawMoneyWay> queryMemberDrawMoneyWayListByMemberId(Long memberId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memberId", memberId);
		return memberDbDao.queryList("MemberDrawMoneyWayMapper.queryMemberDrawMoneyWayListByMemberId",paramMap);
	}

	@Override
	public MemberDrawMoneyWay saveDrawMoneyWay(MemberDrawMoneyWay drawMoneyWay) {
		memberDbDao.insertAndSetupId("MemberDrawMoneyWayMapper.saveDrawMoneyWay", drawMoneyWay);
		return drawMoneyWay;
	}

	@Override
	public MemberDrawMoneyWay queryDrawMoneyWayById(Long id) {
		return memberDbDao.queryUnique("MemberDrawMoneyWayMapper.queryByPrimaryKey", id);
	}

	@Override
	public MemberDrawMoneyWay queryDrawMoneyWayByBankNameAndMemberId(Long memberId, String bankName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", memberId);
		map.put("bankName", bankName);
		return memberDbDao.queryUnique("MemberDrawMoneyWayMapper.queryDrawMoneyWayByBankNameAndMemberId", map);
	}

	@Override
	public boolean updateDrawMoneyWayStatus(Long drawMoneyWayId,Integer oldStatus, Integer newStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", drawMoneyWayId);
		map.put("oldStatus", oldStatus);
		map.put("newStatus", newStatus);
		map.put("updateTime", DateUtil.toOracleDateStr(Calendar.getInstance()));
		return memberDbDao.update("MemberDrawMoneyWayMapper.updateDrawMoneyWayStatus", map) == 1?true:false;
	}
	
	@Override
	public DataPage<MemberDrawMoneyWayResult> queryMemberDrawMoneyWayByPage(MemberDrawMoneyWayQueryOption option,
			DataPage<MemberDrawMoneyWayResult> page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("condition", option);
		map.put("startIndex", (page.getPageNo() - 1) * page.getPageSize());
		map.put("pageSize", page.getPageSize());
		return memberDbDao.queryPage("MemberDrawMoneyWayMapper.queryMemberDrawMoneyWayByPageCount",
				"MemberDrawMoneyWayMapper.queryMemberDrawMoneyWayByPage", map, page);
	}

	@Override
	public boolean updateDrawMoneyWayRecentlyUse(Long drawMoneyWayId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", drawMoneyWayId);
		map.put("updateTime", DateUtil.toOracleDateStr(Calendar.getInstance()));
		return memberDbDao.update("MemberDrawMoneyWayMapper.updateDrawMoneyWayRecentlyUse", map) == 1?true:false;
	}

}
