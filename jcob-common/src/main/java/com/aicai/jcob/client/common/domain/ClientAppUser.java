package com.aicai.jcob.client.common.domain;

import java.io.Serializable;
import java.util.Calendar;
/**
 * app设备对应用户信息
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年4月12日
 */
public class ClientAppUser implements Serializable{
	private static final long serialVersionUID = -864209605114849367L;

	public ClientAppUser(){
		
	}
	public ClientAppUser(String imei,Long memberId){
		this.imei = imei;
		this.memberId = memberId;
	}
    /**  */
    private Long id;

    /** 设备唯一id */
    private String imei;

    /** 用户id */
    private Long memberId;

    /** 创建时间 */
    private Calendar createTime = Calendar.getInstance();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }
}