package com.aicai.jcob.client.common.domain;

import java.io.Serializable;
import java.util.Calendar;

public class ClientSecretKey implements Serializable{


	private static final long serialVersionUID = 4323152584508071117L;

	private Long id ;

	private String sellChannel ;
	
	private String secretKey ;
	
	private int isValid ;
	
	private Calendar expireTime ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSellChannel() {
		return sellChannel;
	}

	public void setSellChannel(String sellChannel) {
		this.sellChannel = sellChannel;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	public Calendar getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Calendar expireTime) {
		this.expireTime = expireTime;
	}
	
	
	
}
