package com.aicai.jcob.member.manager.impl;

import java.net.Inet4Address;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericDao;
import com.aicai.jcob.member.common.domain.MemberOperLog;
import com.aicai.jcob.member.common.domain.constant.OperType;
import com.aicai.jcob.member.common.domain.option.MemberOperLogOption;
import com.aicai.jcob.member.common.domain.option.MemberOperLogQueryOption;
import com.aicai.jcob.member.common.domain.option.MemberOperOption;
import com.aicai.jcob.member.manager.MemberOperLogManager;
import com.aicai.jcob.tjexpert.common.domain.result.TjAttentionLogAdminResult;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class MemberOperLogManagerImpl implements MemberOperLogManager {

	private ExecutorService executorService = Executors.newFixedThreadPool(2);
	@Autowired
    @Qualifier("memberDbDao")
    private GenericDao memberDbDao;
	
	@Override
	public MemberOperLog insertMemberOperLog(MemberOperLog operLog) {
		memberDbDao.insertAndSetupId("MemberOperLogMapper.insertSelective", operLog);
		return operLog;
	}

	/*@Override
	public MemberOperLog updateMemberOperLog(MemberOperLog operLog) {
		memberDbDao.updateByObj("MemberOperLogMapper.updateByPrimaryKeySelective", operLog);
		return operLog;
	}*/

	@Override
	public MemberOperLog queryMemberOperLogById(long id) {
		return (MemberOperLog)memberDbDao.queryUnique("MemberOperLogMapper.selectByPrimaryKey", id);
	}

	@Override
	public List<MemberOperLog> queryMemberOperLogByMemberAndOperTypeCreateTime(
			MemberOperLogOption option) {
		Map<String,Object> param = new HashMap<String,Object>();
		if(null != option.getMemberId()) param.put("memberId", option.getMemberId());
		if(null != option.getOperType()) param.put("operType", option.getOperType());
		if(null != option.getBeginTime()) param.put("beginTime", option.getBeginTime());
		if(null != option.getEndTime()) param.put("endTime", option.getEndTime());
		
		return memberDbDao.queryList("MemberOperLogMapper.queryMemberOperLogByMemberAndOperTypeCreateTime", param);
	}

	@Override
	public void asyncSaveMemberOperLog(Long memberId,MemberOperOption memberOperOption, String remark) {
		Runnable runnable = new Runnable() {
			public void run() {
				MemberOperLog memberOperLog = new MemberOperLog();
				memberOperLog.setChannel(memberOperOption.getChannel());
				memberOperLog.setClientType(memberOperOption.getClientType());
				memberOperLog.setUserIp(memberOperOption.getUserIp());
				memberOperLog.setFrontServerIp(memberOperOption.getFrontServerIp());
				memberOperLog.setBackServerIp(getLocalIp());//TODO 调用工具类，本地获取
				memberOperLog.setOperType(memberOperOption.getOperType());
				memberOperLog.setMemberId(memberId);
				memberOperLog.setRemark(remark);
				insertMemberOperLog(memberOperLog);
			}
		};
		executorService.submit(runnable);
	}
	
	  private static final String getLocalIp() {
	        try {
	            for (Enumeration e = NetworkInterface.getNetworkInterfaces(); e.hasMoreElements();) {
	                NetworkInterface item = (NetworkInterface) e.nextElement();

	                //非虚拟非回路并开启状态
	                if((!item.isVirtual()) && item.isUp() && (!item.isLoopback())){
	                    for (InterfaceAddress address : item.getInterfaceAddresses()) {
	                        if (address.getAddress() instanceof Inet4Address) {
	                            Inet4Address inet4Address = (Inet4Address) address.getAddress();

	                            if(inet4Address.isSiteLocalAddress()){
	                               return inet4Address.getHostAddress();
	                           }

	                        }
	                    }
	                }
	            }
	        } catch (Exception ex) {
	        	ex.printStackTrace();
	        }
	        return null;
	    }

	@PreDestroy
	public void destroy() {
		executorService.shutdown();
	}

	@Override
	public DataPage<TjAttentionLogAdminResult> pageAttentionOperLog(
			MemberOperLogQueryOption option, DataPage<TjAttentionLogAdminResult> page) {
		Map<String, Object> paras = new HashMap<String, Object>();
		if(null != option.getMemberIdArr()) paras.put("memberIdArr", option.getMemberIdArr());
		if(null != option.getBeginTime()) paras.put("beginTime", option.getBeginTime());
		if(null != option.getEndTime()) paras.put("endTime", option.getEndTime());
		if(null != option.getRemark()) paras.put("remark", option.getRemark());
		return (DataPage<TjAttentionLogAdminResult>)memberDbDao.queryPage("MemberOperLogMapper.countAttentionOperLog", "MemberOperLogMapper.pageAttentionOperLog", paras, page);
	}

	@Override
	public ModelResult<String> getMemberRegisterUserIp(long memberId) {
		MemberOperLogOption option = new MemberOperLogOption();
		option.setMemberId(memberId);
		option.setOperType(OperType.register.getIndex());
		List<MemberOperLog> operLogs = this.queryMemberOperLogByMemberAndOperTypeCreateTime(option);
		if(CollectionUtils.isEmpty(operLogs)) return new ModelResult<String>(null);
		return new ModelResult<String>(operLogs.get(0).getUserIp());
	}

	@Override
	public void saveSystemAutoLoginLog(long memberId) {
		MemberOperLog systemAutoLoginLog = new MemberOperLog();
		systemAutoLoginLog.setMemberId(memberId);
		systemAutoLoginLog.setCreateTime(Calendar.getInstance());
		systemAutoLoginLog.setOperType(OperType.system_auto_login.getIndex());
		this.insertMemberOperLog(systemAutoLoginLog);
	}
}
