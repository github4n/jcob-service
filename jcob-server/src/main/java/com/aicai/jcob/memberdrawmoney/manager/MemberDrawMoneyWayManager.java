package com.aicai.jcob.memberdrawmoney.manager;

import java.util.List;

import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.memberdrawmoney.common.domain.MemberDrawMoneyWay;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyWayQueryOption;
import com.aicai.jcob.memberdrawmoney.common.domain.result.MemberDrawMoneyWayResult;

/**
 * 提款方式管理接口
 * 
 * @project jcob-server
 * @author duannp
 * @date 2016年2月23日
 */
public interface MemberDrawMoneyWayManager {
	/**
	 * 查询用户提款方式列表
	 * @param memberId
	 * @return
	 */
	public List<MemberDrawMoneyWay> queryMemberDrawMoneyWayListByMemberId(Long memberId);

	/**
	 * 保存提款方式
	 * @param drawMoneyWay
	 * @return
	 */
	public MemberDrawMoneyWay saveDrawMoneyWay(MemberDrawMoneyWay drawMoneyWay);
	
	/**
	 * 查询提款方式记录
	 * @param drawMoneyWayId
	 * @return
	 */
	public MemberDrawMoneyWay queryDrawMoneyWayById(Long drawMoneyWayId);
	/**
	 * 按用户和银行名称查询提款绑定信息
	 * @param memberId
	 * @param bankName
	 * @return
	 */
	public MemberDrawMoneyWay queryDrawMoneyWayByBankNameAndMemberId(Long memberId,String bankName);
	
	/**
	 * 修改提款银行卡状态
	 * @param drawMoneyWayId
	 * @param oldStatus
	 * @param newStatus
	 * @return
	 */
	public boolean updateDrawMoneyWayStatus(Long drawMoneyWayId,Integer oldStatus,Integer newStatus);
	
	/**
	 * 分页查询用户提款方式
	 * 
	 * @param option
	 * @param page
	 * @return
	 */
	public DataPage<MemberDrawMoneyWayResult> queryMemberDrawMoneyWayByPage(MemberDrawMoneyWayQueryOption option,
			DataPage<MemberDrawMoneyWayResult> page);
	
	/**
	 * 更新提款方式为最近使用
	 * @param drawMoneyWayId
	 * @return
	 */
	public boolean updateDrawMoneyWayRecentlyUse(Long drawMoneyWayId);
}
