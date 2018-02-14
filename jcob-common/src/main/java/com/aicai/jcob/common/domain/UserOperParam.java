package com.aicai.jcob.common.domain;

import java.io.Serializable;

import com.aicai.jcob.common.constant.ClientType;

/**
 * 用户操作参数
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月30日
 * Copyright (C) 2010-2012 www.2caipiao.com Inc. All rights reserved.
 */
public class UserOperParam implements Serializable{
	private static final long serialVersionUID = 3627938313558878931L;
	/**
	 * 用户id
	 */
	private Long memberId;
	/**
	 * 客户端类型
	 */
	private ClientType clientType;
	/**
	 * 客户端版本
	 */
	private String clientVersion = "0.0";
	/**
	 * 销售来源渠道
	 */
    private Integer channel = 1;
    
    /** 前端接口填  用户ip */
    private String userIp;

    /** 前端接口填   接口服务器ip */
    private String frontServerIp;

    /**后端服务填   后端服务ip  */
    private String backServerIp;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
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
    
}
