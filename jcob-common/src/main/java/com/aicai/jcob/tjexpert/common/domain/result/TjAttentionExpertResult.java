package com.aicai.jcob.tjexpert.common.domain.result;  

import java.io.Serializable;
import java.util.Date;

import com.aicai.jcob.tjexpert.common.domain.utils.ExpertRecordUtils;

/** 
 * @author jing.ming
 * @version 创建时间：2016年2月29日 下午2:49:34 
 * 返回前台的用户关注与粉丝
 */
public class TjAttentionExpertResult implements Serializable{

	private static final long serialVersionUID = 2296479480216973865L;
	
	private String icon ;
	private String expertDesc ;
	
	/** memberId */
    private Long id;
	/**  */
    private Date createTime ;

    /**  */
    private Date updateTime ;
	private Long memberId ; //粉丝
	
	private Long attentionMemberId ; //明星
	/**
	 * 专家等级
	 */
	private Integer expertLevel ;
	/**
	 * 昵称
	 */
	private String nickName ;
	
	private String phone ; //手机号
	/**
	 * 战绩:如11001
	 */
	private String record ;	
	/**
	 * 当前用户是否关注
	 */
	private boolean isAttention = false ;
	public String getIcon() {
		return icon;
	}
	public String getExpertDesc() {
		return expertDesc;
	}
	public Integer getExpertLevel() {
		return expertLevel;
	}
	public String getNickName() {
		return nickName;
	}
	public String getRecord() {
		return record ;
	}

	public String getFormatRecord(){
		return ExpertRecordUtils.getFormatRecord(record);
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setExpertDesc(String expertDesc) {
		this.expertDesc = expertDesc;
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

	public boolean isAttention() {
		return isAttention;
	}
	public void setAttention(boolean isAttention) {
		this.isAttention = isAttention;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Long getAttentionMemberId() {
		return attentionMemberId;
	}
	public void setAttentionMemberId(Long attentionMemberId) {
		this.attentionMemberId = attentionMemberId;
	}
	public Long getId() {
		return id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
 