package com.aicai.jcob.memberwallet.manager;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.memberwallet.common.domain.MemberWalletLog;
import com.aicai.jcob.memberwallet.common.domain.constant.BizType;
import com.aicai.jcob.memberwallet.common.domain.constant.WalletOpType;
import com.aicai.jcob.memberwallet.common.domain.option.PageWalletLogOption;
import com.aicai.jcob.memberwallet.common.domain.result.PageWalletLogVo;

/**
 * 现金钱包流水管理接口
 * 
 * @project jcob-server
 * @author duannp
 * @date 2016年1月27日
 */
public interface MemberWalletLogManager {
	
	/**
	 * 分页查询用户钱包流水
	 * @param memberId
	 * 用户id不能为空
	 * @param bizTypeList
	 * @param walletOpTypeList
	 * @param beginTime
	 * 开始时间不能为空
	 * @param endTime
	 * 截至时间不能为空
	 * @param dataPage
	 * @return
	 */
	public DataPage<MemberWalletLog> pageMemberWalletLog(Long memberId,List<BizType> bizTypeList,List<WalletOpType> walletOpTypeList,Calendar beginTime,Calendar endTime,DataPage<MemberWalletLog> dataPage);
	/**
	 * 保存钱包流水
	 * @param memberWalletLog
	 * @create_time 2016年1月27日 下午4:21:22
	 */
	public void saveWalletLog(MemberWalletLog memberWalletLog);
	
	/**
	 * 通过业务类型查询钱包流水
	 * @param memberId
	 * @param bizType
	 * @param bizId
	 * @param walletOpType
	 * @return
	 */
	public MemberWalletLog queryMemberWalletLogByBiz(Long memberId,BizType bizType,Long bizId,WalletOpType walletOpType);
	/**
	 * 统计用户时间范围内收益
	 * @param memberId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public BigDecimal sumMemberProfit(Long memberId,Calendar startDate,Calendar endDate);
	
	/**
	 * 统计用户流水金额总和
	 * 返回内容  map<BizType.index,金额>
	 * @param memberId
	 * @param startDate
	 * @param endDate
	 * @return  
	 */
	public Map<Integer, BigDecimal> sumMemberWalletLogAmount(Long memberId,Calendar startDate,Calendar endDate);
	
	/**
	 * 后台钱包流水查询
	 * @param pageWalletLogOption
	 * @param dataPage
	 * @return
	 */
	public DataPage<PageWalletLogVo> adminPageMemberWalletLog(PageWalletLogOption pageWalletLogOption,DataPage<PageWalletLogVo> dataPage);
	
	/**
	 * 后台钱包流水金额总和
	 * @param pageWalletLogOption
	 * @return
	 */
	public PageWalletLogVo adminSumMemberWalletLog(PageWalletLogOption pageWalletLogOption);
}
