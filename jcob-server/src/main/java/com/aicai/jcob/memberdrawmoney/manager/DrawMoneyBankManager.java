package com.aicai.jcob.memberdrawmoney.manager;

import java.util.List;

import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.memberdrawmoney.common.domain.DrawMoneyBank;
import com.aicai.jcob.memberdrawmoney.common.domain.DrawMoneyBranchBank;
import com.aicai.jcob.memberdrawmoney.common.domain.DrawMoneyBranchBankVo;
import com.aicai.jcob.memberdrawmoney.common.domain.DrawMoneyProvinceCity;

public interface DrawMoneyBankManager {

	/**
	 * 查询所有的支持的提款银行名称列表 供前端展示选择
	 * 
	 * @return
	 */
	public List<DrawMoneyBank> queryDrawMoneyBank();
	/**
	 * 查询所有的支持的提款银行名称列表 供前端展示选择
	 * 
	 * @return
	 */
	public List<DrawMoneyBank> queryDrawMoneyBankisMigrate(boolean isMigrate);
	/**
	 * 查询所有的支持的提款银行省市
	 * 
	 * @return
	 */
	public List<DrawMoneyProvinceCity> queryProvinceCityList();

	/**
	 * 查询对应银行省市所在分支行
	 * 
	 * @param cityId
	 *            省市对应表DrawMoneyProvinceCity的ID,不允许为null
	 * @param bankId
	 *            提款银行表DrawMoneyBank的ID,不允许为null
	 * @param dataPage 支持WAP分页 NULL时不分页        
	 * @return
	 */
	public DataPage<DrawMoneyBranchBank> queryDrawMoneyBranchBank(
			int cityId, int bankId, DataPage<DrawMoneyBranchBank> dataPage);

	/**
	 * 查询所有省份
	 * 
	 * @param dataPage
	 * @param includeCity
	 *            1包括市，0不包括市，只有省；为支持不再次请求市数据需求
	 * @param dataPage 支持WAP分页 NULL时不分页 
	 * @return
	 */
	public DataPage<DrawMoneyProvinceCity> queryDrawMoneyProvince(
			DataPage<DrawMoneyProvinceCity> dataPage, int includeCity);

	/**
	 * 查询省份所在市
	 * 
	 * @param provinceId
	 *            省ID
	 * @param dataPage 支持WAP分页 NULL时不分页 
	 * @return
	 */

	public DataPage<DrawMoneyProvinceCity> queryDrawMoneyCity(int provinceId,
			DataPage<DrawMoneyProvinceCity> dataPage);
	/**
	 * 新增支行
	 * @param drawMoneyBranchBank
	 */
	public void insertBranchBank(DrawMoneyBranchBank drawMoneyBranchBank);
	/**
	 * 通过id查询支行
	 * @param drawMoneyBranchBank
	 */
	public DrawMoneyBranchBank queryBranchBankById(long id);
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
}
