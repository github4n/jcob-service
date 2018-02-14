package com.aicai.jcob.tjexpert.common.domain.result;  

import java.io.Serializable;
import java.util.Date;

/** 
 * @author jing.ming
 * @version 创建时间：2016年2月26日 下午2:08:23 
 * 后台关注操作管理result 
 */
public class TjAttentionExpertAdminResult implements Serializable{

	private static final long serialVersionUID = 1583041377495764916L;
	
	/**粉丝用户id*/
	private Long memberId ;
	/**
	 * 明星memberId
	 */
	private Long attentionMemberId ;

	/**粉丝昵称*/
	private String nickName ;
	/**明星昵称*/
	private String attentionNickName ;

	/**用户当前身份*/
	private Integer level ;
	/**关注状态 0:取关 ,1:关注*/ 
	private Integer attentionStatus ;
	/**
	 * 最近关注时间
	 */
	private Date attentionTime ;
	/**
	 * 操作记录时间
	 */
	private Date attentionOperTime ;

	public Long getMemberId() {
		return memberId;
	}

	public String getNickName() {
		return nickName;
	}

	public Integer getLevel() {
		return level;
	}

	public Integer getAttentionStatus() {
		return attentionStatus;
	}

	public Date getAttentionTime() {
		return attentionTime;
	}

	public Date getAttentionOperTime() {
		return attentionOperTime;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setAttentionStatus(Integer attentionStatus) {
		this.attentionStatus = attentionStatus;
	}

	public void setAttentionTime(Date attentionTime) {
		this.attentionTime = attentionTime;
	}

	public void setAttentionOperTime(Date attentionOperTime) {
		this.attentionOperTime = attentionOperTime;
	}

	public Long getAttentionMemberId() {
		return attentionMemberId;
	}

	public String getAttentionNickName() {
		return attentionNickName;
	}

	public void setAttentionMemberId(Long attentionMemberId) {
		this.attentionMemberId = attentionMemberId;
	}

	public void setAttentionNickName(String attentionNickName) {
		this.attentionNickName = attentionNickName;
	}
	

}
 