package com.aicai.jcob.member.common.domain.option;

import java.io.Serializable;

import com.aicai.appmodel.clusterutil.NetUtil;
import com.aicai.jcob.common.constant.ClientType;
import com.aicai.jcob.member.common.domain.constant.OperType;

public class MemberOperOption implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	/** 操作类型: 0 注册，1登录，2发布推荐方案，3查看专家方案，4关注专家，5取消专家关注，6充值，7提款，8修改用户信息 */
    private Integer operType = OperType.login.getIndex();

    /** 操作平台 */
    private Integer clientType = ClientType.android.getIndex();

    /** 渠道 */
    private Integer channel = 1;

    /** 用户ip */
    private String userIp;

    /** 接口服务器ip */
    private String frontServerIp = NetUtil.getMyIp();

    /**  */
    private String backServerIp;

    /** 备注 */
    private String remark;
    
    
    public MemberOperOption(Integer channel,Integer clientType,String userIp ,String frontServerIp){
    	this.channel = channel;
    	this.clientType= clientType;
    	this.userIp = userIp;
    	this.frontServerIp = frontServerIp;
    	
    }
    
	public Integer getOperType() {
		return operType;
	}

	public void setOperType(Integer operType) {
		this.operType = operType;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getFrontServerIp() {
		return frontServerIp;
	}

	public void setFrontServerIp(String frontServerIp) {
		this.frontServerIp = frontServerIp;
	}

	public String getBackServerIp() {
		return backServerIp;
	}

	public void setBackServerIp(String backServerIp) {
		this.backServerIp = backServerIp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
    
    
    
}
