package com.aicai.jcob.memberdrawmoney.common.service;

import java.math.BigDecimal;
import java.util.List;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.member.common.domain.option.MemberOperOption;
import com.aicai.jcob.memberdrawmoney.common.domain.DrawMoneyBank;
import com.aicai.jcob.memberdrawmoney.common.domain.DrawMoneyBranchBank;
import com.aicai.jcob.memberdrawmoney.common.domain.DrawMoneyBranchBankVo;
import com.aicai.jcob.memberdrawmoney.common.domain.DrawMoneyProvinceCity;
import com.aicai.jcob.memberdrawmoney.common.domain.MemberDrawMoneyLog;
import com.aicai.jcob.memberdrawmoney.common.domain.MemberDrawMoneyWay;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyAuditInfoOption;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyInfoUpdateOption;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyLogQueryOption;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyMannualUpdateOption;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyWayAddOption;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyWayQueryOption;
import com.aicai.jcob.memberdrawmoney.common.domain.result.MemberDrawMoneyLogDetail;
import com.aicai.jcob.memberdrawmoney.common.domain.result.MemberDrawMoneyLogResult;
import com.aicai.jcob.memberdrawmoney.common.domain.result.MemberDrawMoneyWayResult;

/**
 * 提款业务接口
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年2月22日
 */
public interface MemberDrawMoneyWriteService {
	/**
	 *  用户提款
	 * @param memberId
	 * @param drawAmount
	 * 提款金额
	 * @param handingCost
	 * 提款手续费
	 * @param drawMoneyWayId
	 * 提款方式id
	 * @param operOption
	 * @return
	 * @create_time 2016年2月23日 上午10:08:44
	 */
	public ModelResult<MemberDrawMoneyLog> memberDrawMoney(Long memberId,BigDecimal drawAmount,BigDecimal handingCost,Long drawMoneyWayId,MemberOperOption operOption);

	
	/**
	 * 查询用户提款绑定方式
	 * @param memberId
	 * @return
	 */
	public ModelResult<List<MemberDrawMoneyWay>> queryMemberDrawMoneyWayListByMemberId(Long memberId);
	/**
	 * 保存用户提款方式
	 * @param memberId
	 * @param drawMoneyWayAddOption
	 *  提款方式参数
	 * @param operOption
	 * @return
	 */
	public ModelResult<MemberDrawMoneyWay> saveMemberDrawMoneyWay(Long memberId,MemberDrawMoneyWayAddOption drawMoneyWayAddOption , MemberOperOption operOption);
	
	/**
	 * 查询用户提款记录详情
	 * @param drawMoneyLogId
	 * @return
	 */
	public ModelResult<MemberDrawMoneyLogDetail> queryDrawMoneyLogDetailById(Long drawMoneyLogId);
	/**
	 * 删除用户提款绑定方式
	 * @param memberId
	 * @param drawMoneyWayId
	 * @return
	 */
	public ModelResult<Boolean> deleteMemberDrawMoneyWay(Long memberId,Long drawMoneyWayId,MemberOperOption operOption);
	
	/**
	 * 分页查询用户提款方式
	 * @return
	 */
	public PageResult<MemberDrawMoneyWayResult> queryMemberDrawMoneyWayByPage(MemberDrawMoneyWayQueryOption option, DataPage<MemberDrawMoneyWayResult> page);
	
	/**
	 * 分页查询用户提款
	 * @return
	 */
	public PageResult<MemberDrawMoneyLogResult> queryMemberDrawMoneyLogByPage(MemberDrawMoneyLogQueryOption option, DataPage<MemberDrawMoneyLogResult> page);
	
	/**
	 * 统计用户提款
	 * @return
	 */
	public ModelResult<MemberDrawMoneyLogResult> statistMemberDrawMoneyLog(MemberDrawMoneyLogQueryOption option);
	
	/**
	 * 修改审核信息
	 * @return
	 */
	public ModelResult<Boolean> updateDrawMoneyLogAuditInfo(MemberDrawMoneyAuditInfoOption option);
	
	/**
	 * 提款状态相关信息修改
	 * @param updateInfoOption
	 * @return
	 */
	public ModelResult<Boolean> updateDrawMoneyStatus(MemberDrawMoneyInfoUpdateOption updateInfoOption );
	
	/**
	 * 人工下发，修改提款信息
	 * @param updateInfoOption
	 * @return
	 */
	public ModelResult<Boolean> updateDrawMoneyWithManual(MemberDrawMoneyMannualUpdateOption mannualUpdateOption );
	
	/**
	 * 提款失败要退款
	 * @param drawMoneyLogId
	 * @return
	 */
	public ModelResult<Boolean> refundDrawMoney(Long drawMoneyLogId);
	
	/**
	 * 通过流水编号查询记录
	 * @param drawMoneyNO
	 * @return
	 */
	public ModelResult<MemberDrawMoneyLog> queryDrawMoneyLogByDrawMoneyNo(String drawMoneyNO);
	
	/**
	 * 查询所有的支持的提款银行名称列表 供前端展示选择
	 * @return
	 */
	public ModelResult<List<DrawMoneyBank>> queryDrawMoneyBank();
	
	
	/**
	 * 查询对应银行省市所在分支行
	 * 
	 * @param cityId
	 *            省市对应表DrawMoneyProvinceCity的ID,不允许为null
	 * @param bankId
	 *            提款银行表DrawMoneyBank的ID,不允许为null
	 * @param dataPage 支持WAP分页,不分页时，请pagesize传 100000，足够大查出所有数据   
	 * @return
	 */
	public PageResult<DrawMoneyBranchBank> queryDrawMoneyBranchBank(
			int cityId, int bankId, DataPage<DrawMoneyBranchBank> dataPage);

	
	
	/**
	 * 查询所有省份
	 * 
	 * @param dataPage
	 * @param includeCity
	 *            1包括市，0不包括市，只有省；为支持不再次请求市数据需求
	 * @param dataPage 支持WAP分页,不分页时，请pagesize传 100000，足够大查出所有数据
	 * @return
	 */
	public PageResult<DrawMoneyProvinceCity> queryDrawMoneyProvince(
			DataPage<DrawMoneyProvinceCity> dataPage, int includeCity);

	/**
	 * 查询省份所在市
	 * 
	 * @param provinceId
	 *            省ID
	 * @param dataPage 支持WAP分页,不分页时，请pagesize传 100000，足够大查出所有数据 
	 * @return
	 */

	public PageResult<DrawMoneyProvinceCity> queryDrawMoneyCity(int provinceId,
			DataPage<DrawMoneyProvinceCity> dataPage);

	/**
	 * 新增支行
	 * @param drawMoneyBranchBank
	 */
	public void insertBranchBank(DrawMoneyBranchBank drawMoneyBranchBank);
	/**
	 * 更新支行
	 * @param drawMoneyBranchBank
	 */
	public void updateBranchBank(DrawMoneyBranchBank drawMoneyBranchBank);
	/**
	 * 分支行分页查询
	 * 
	 * @param cityId
	 *            
	 * @param bankId
	 *            
	 * @param name
	 *            
	 * @param dataPage        
	 * @return
	 */
	public DataPage<DrawMoneyBranchBankVo> queryDrawMoneyBranchBankDataPage(
			Integer cityId, Integer bankId,String name, DataPage<DrawMoneyBranchBankVo> dataPage);
	
	/**
	 * 查询所有的支持的提款银行省市
	 * 
	 * @return
	 */
	public List<DrawMoneyProvinceCity> queryProvinceCityList();
	/**
	 * 通过id查询支行
	 * @param drawMoneyBranchBank
	 */
	public DrawMoneyBranchBank queryBranchBankById(long id);
	
	/**
	 * 查询对应银行省市所在分支行
	 * 
	 * @param cityId
	 *            省市对应表DrawMoneyProvinceCity的ID,不允许为null
	 * @param bankId
	 *            提款银行表DrawMoneyBank的ID,不允许为null
	 * @param branchName
	 *            提款分支行模糊条件 
	 * @param dataPage   
	 * @return
	 */
	public PageResult<DrawMoneyBranchBank> queryDrawMoneyBranchBank(
			int cityId, int bankId, String branchName,DataPage<DrawMoneyBranchBank> dataPage);
	
}
