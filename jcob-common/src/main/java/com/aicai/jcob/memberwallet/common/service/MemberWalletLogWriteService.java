package com.aicai.jcob.memberwallet.common.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.memberwallet.common.domain.MemberWalletLog;
import com.aicai.jcob.memberwallet.common.domain.constant.BizType;
import com.aicai.jcob.memberwallet.common.domain.constant.WalletOpType;
import com.aicai.jcob.memberwallet.common.domain.option.PageWalletLogOption;
import com.aicai.jcob.memberwallet.common.domain.result.PageWalletLogVo;
/**
 * 用户现金钱包流水service
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月29日
 */
public interface MemberWalletLogWriteService {
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
	public PageResult<MemberWalletLog> pageMemberWalletLog(Long memberId,List<BizType> bizTypeList,List<WalletOpType> walletOpTypeList,Calendar beginTime,Calendar endTime,DataPage<MemberWalletLog> dataPage);
	
	
	/**
	 * 通过业务类型查询钱包流水
	 * @param memberId
	 * @param bizType
	 * @param bizId
	 * @param walletOpType
	 * @return
	 */
	public ModelResult<MemberWalletLog> queryMemberWalletLogByBiz(Long memberId,BizType bizType,Long bizId,WalletOpType walletOpType);

	/**
	 * 统计今日用户收益
	 * @param memberId
	 * @return
	 */
	public ModelResult<BigDecimal> sumMemberTodayProfit(Long memberId);
	/**
	 * 统计用户流水金额总和
	 * 返回内容  map<WalletOpType.index,金额>
	 * @param memberId
	 * @param startDate
	 * @param endDate
	 * @return  
	 */
	public ModelResult<Map<Integer, BigDecimal>> sumMemberWalletLogAmount(Long memberId,Calendar startDate,Calendar endDate);
	/**
	 * 后台钱包流水查询
	 * @param pageWalletLogOption
	 * @param dataPage
	 * @return
	 */
	public PageResult<PageWalletLogVo> adminPageMemberWalletLog(PageWalletLogOption pageWalletLogOption,DataPage<PageWalletLogVo> dataPage);
	
	/**
	 * 后台钱包流水金额总和
	 * 
	 * @param pageWalletLogOption
	 * @return
	 */
	public ModelResult<PageWalletLogVo> adminSumMemberWalletLog(PageWalletLogOption pageWalletLogOption);
}
