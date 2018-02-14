package com.aicai.jcob.tjexpert.common.domain;  

import java.io.Serializable;
import java.util.Date;

/** 
 * @author jing.ming
 * @version 创建时间：2016年2月3日 上午10:50:27 
 * 后台设置专家的特殊操作表
 * 程序的简单说明 
 */
public class TjExpertSetInfo implements Serializable {

	private static final long serialVersionUID = 6534615224917106088L;
	
	private Long id ;
	private Long memberId ;
	private Integer level ;
	private String description ;
	private String operaterName ;
	private Date createTime ;
	private Date updateTime ;
	public Long getId() {
		return id;
	}
	public Long getMemberId() {
		return memberId;
	}
	public Integer getLevel() {
		return level;
	}
	public String getDescription() {
		return description;
	}
	public String getOperaterName() {
		return operaterName;
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
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setOperaterName(String operaterName) {
		this.operaterName = operaterName;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	

}
 