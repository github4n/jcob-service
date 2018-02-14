package com.aicai.jcob.tjexpert.common.domain.result;  

import java.io.Serializable;
import java.util.Date;

/** 
 * @author jing.ming
 * @version 创建时间：2016年3月7日 下午6:33:15 
 * 程序的简单说明 
 */
public class TjExpertSetAdminResult implements Serializable{

	private static final long serialVersionUID = 9216933528877714917L;
	/**  */
    private Long memberId;

    private String nickName ;
    /** 等级：0普通用户，1实验室专家，2正式专家,3带v的正式专家 */
    private Integer level ;
    private String description ;
	private String operaterName ;
	private Date createTime   ;
	private Date updateTime ;
	public Long getMemberId() {
		return memberId;
	}
	public String getNickName() {
		return nickName;
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
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	public Date getCreateTime() {
		return createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	

}
 