package com.aicai.jcob.memberdrawmoney.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericMybatisDao;
import com.aicai.jcob.memberdrawmoney.common.domain.DrawMoneyBank;
import com.aicai.jcob.memberdrawmoney.common.domain.DrawMoneyBranchBank;
import com.aicai.jcob.memberdrawmoney.common.domain.DrawMoneyBranchBankVo;
import com.aicai.jcob.memberdrawmoney.common.domain.DrawMoneyProvinceCity;
import com.aicai.jcob.memberdrawmoney.manager.DrawMoneyBankManager;

public class DrawMoneyBankManagerImpl implements DrawMoneyBankManager {

	@Autowired
	@Qualifier("memberDbDao")
	private GenericMybatisDao  memberDbDao;

	@Override
	public List<DrawMoneyBank> queryDrawMoneyBank() {

		return memberDbDao.queryList("DrawMoneyBankMapper.queryBankList");
	}
	@Override
	public List<DrawMoneyBank> queryDrawMoneyBankisMigrate(boolean isMigrate){
		List<Integer>  bankFlagList = new ArrayList<Integer>();
	/**
	 * 提款银行标记
	 * 0、全部支持
	 * 1、只支持支付宝提款
	 * 2、只支持新浪支付提款
	 * */
 
		if(isMigrate){
			bankFlagList.add(0);
			bankFlagList.add(2);
		}else{
			bankFlagList.add(0);
			bankFlagList.add(1);
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("bankFlagList", bankFlagList);
		return memberDbDao.queryList("DrawMoneyBankMapper.queryBankListByBankFlag",paramMap);
	}
	@Override
	public List<DrawMoneyProvinceCity> queryProvinceCityList() {
		
		return memberDbDao.queryList("DrawMoneyBankMapper.queryProvinceCityList");
	}

	@Override
	public DataPage<DrawMoneyBranchBank> queryDrawMoneyBranchBank(int cityId,
			int bankId, DataPage<DrawMoneyBranchBank> dataPage) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cityId", cityId);
		paramMap.put("bankId", bankId);

		return memberDbDao.queryPage("DrawMoneyBankMapper.queryBranchBankCount",
				"DrawMoneyBankMapper.queryBranchBankPage", paramMap, dataPage);
	}

	@Override
	public DataPage<DrawMoneyProvinceCity> queryDrawMoneyProvince(
			DataPage<DrawMoneyProvinceCity> dataPage, int includeCity) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("includeCity", includeCity);

		return memberDbDao.queryPage("DrawMoneyBankMapper.queryProvinceCityCount",
				"DrawMoneyBankMapper.queryProvinceCityPage", paramMap, dataPage);
	}

	@Override
	public DataPage<DrawMoneyProvinceCity> queryDrawMoneyCity(int provinceId,
			DataPage<DrawMoneyProvinceCity> dataPage) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("provinceId", provinceId);

		return memberDbDao.queryPage("DrawMoneyBankMapper.queryProvinceCityCount",
				"DrawMoneyBankMapper.queryProvinceCityPage", paramMap, dataPage);
	}

	@Override
	public DataPage<DrawMoneyBranchBankVo> queryDrawMoneyBranchBankDataPage(
			Integer cityId, Integer bankId, String name,
			DataPage<DrawMoneyBranchBankVo> dataPage) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(cityId!=null){
			paramMap.put("cityId", cityId);
		}
		if(bankId!=null){
		paramMap.put("bankId", bankId);
		}
		if(name!=null&&!"".equals(name)){
		paramMap.put("name", name);
		}
		return memberDbDao.queryPage("DrawMoneyBankMapper.queryBranchBankCountVo",
				"DrawMoneyBankMapper.queryBranchBankPageVo", paramMap, dataPage);
	}

	@Override
	public void insertBranchBank(DrawMoneyBranchBank drawMoneyBranchBank) {
		int branchId = memberDbDao.queryOne(
				"DrawMoneyBankMapper.selectPrimaryKey",
				new HashMap<String, Object>());
		drawMoneyBranchBank.setId(branchId);
		memberDbDao.insertAndSetupId("DrawMoneyBankMapper.insertBranchBank",
				drawMoneyBranchBank);
	}

	@Override
	public void updateBranchBank(DrawMoneyBranchBank drawMoneyBranchBank) {
		memberDbDao.updateByObj("DrawMoneyBankMapper.updateBranchBank", drawMoneyBranchBank);
	}
	@Override
	public DrawMoneyBranchBank queryBranchBankById(long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return (DrawMoneyBranchBank)memberDbDao.queryObject("DrawMoneyBankMapper.queryBranchBankById", paramMap);
	}

}
