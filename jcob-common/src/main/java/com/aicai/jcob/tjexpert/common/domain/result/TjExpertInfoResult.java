package com.aicai.jcob.tjexpert.common.domain.result;  

import java.io.Serializable;

import com.aicai.jcob.tjexpert.common.domain.utils.ExpertRecordUtils;

/** 
 * @author jing.ming
 * @version 创建时间：2016年2月23日 下午3:55:23 
 * 前端专家信息组装类 
 */
public class TjExpertInfoResult implements Serializable{

	private static final long serialVersionUID = -7878843868808529515L;
	/**
	 * 专家memberId
	 */
	private Long expertMemberId ;
    /**
     * 专家头像
     */
	private String icon ;
	/**
	 * 专家等级
	 */
	private Integer expertLevel ;
	/**
	 * 昵称
	 */
	private String nickName ;
	
	private String phone ; //手机号码
	/**
	 * 近5中几,不满5场:01010
	 */
	private String record ;
	/**
	 * 关注的总人数
	 */
	private Integer attentionSum ;
	/**
	 * 当前用户是否关注
	 */
	private boolean isAttention = false ;
	/**
	 * 专家描述
	 */
	private String expertDesc ;
	public String getIcon() {
		return icon;
	}
	public Integer getExpertLevel() {
		return expertLevel;
	}
	public String getNickName() {
		return nickName;
	}
	public String getRecord() {
		return record;
	}
	

	public String getFormatRecord(){
		return ExpertRecordUtils.getFormatRecord(record);
	}
	
	
	public Integer getAttentionSum() {
		return attentionSum;
	}
	public boolean isAttention() {
		return isAttention;
	}
	public String getExpertDesc() {
		return expertDesc;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setExpertLevel(Integer expertLevel) {
		this.expertLevel = expertLevel;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public void setAttentionSum(Integer attentionSum) {
		this.attentionSum = attentionSum;
	}
	public void setAttention(boolean isAttention) {
		this.isAttention = isAttention;
	}
	public void setExpertDesc(String expertDesc) {
		this.expertDesc = expertDesc;
	}
	public Long getExpertMemberId() {
		return expertMemberId;
	}
	public void setExpertMemberId(Long expertMemberId) {
		this.expertMemberId = expertMemberId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
 