package com.aicai.jcob.tjexpert.common.domain.result;  

import java.io.Serializable;

/** 
 * @author jing.ming
 * @version 创建时间：2016年3月1日 上午9:46:57 
 * 后台运营编辑推荐专家信息 
 */
public class TjExpertRecommendResult implements Serializable{

	private static final long serialVersionUID = -7285336133820813835L;
	/**
	 * 对应Tj_expert_info表的id
	 */
	private Long id ;
	/**
	 * 专家memberId
	 */
	private Long memberId ;
	/**
	 * 专家等级
	 */
	private Integer level ;
	/**
	 * 头像
	 */
	private String icon ;
	/**
	 * 昵称
	 */
	private String nickName ;
	/**
	 * 手机号
	 */
	private String phone ;
	
	public Long getId() {
		return id;
	}
	public Long getMemberId() {
		return memberId;
	}
	public Integer getLevel() {
		return level;
	}
	public String getIcon() {
		return icon;
	}
	public String getNickName() {
		return nickName;
	}
	public String getPhone() {
		return phone;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	

}
 