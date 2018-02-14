package com.aicai.jcob.tjexpert.common.domain.result;  

import java.io.Serializable;
import java.util.Date;

import com.aicai.jcob.member.common.utils.MemberLogRemarkUtils;

/** 
 * @author jing.ming
 * @version 创建时间：2016年2月29日 下午5:51:37 
 * 后台关注操作返回result
 */
public class TjAttentionLogAdminResult implements Serializable{

	private static final long serialVersionUID = 3322650859590369370L;
	/**  */
    private Long id;

    /**  */
    private Long memberId;
    private Integer operType ;
    
    private String nickName ;
    /**
     * 明星
     */
    private Long attentionMemberId ;
    private String attentionNickName ;
    /** 操作平台 */
    private Integer clientType ;
    /**  */
    private Date createTime;
    /**
     * 备注:memberId,nickName
     */
    private String remark ;
    
	public Long getId() {
		return id;
	}
	public Long getMemberId() {
		return memberId;
	}
	public Integer getOperType() {
		return operType;
	}
	public String getNickName() {
		return nickName;
	}

	public Integer getClientType() {
		return clientType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public void setOperType(Integer operType) {
		this.operType = operType;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public void setAttentionMemberId(Long attentionMemberId) {
		this.attentionMemberId = attentionMemberId;
	}
	public void setAttentionNickName(String attentionNickName) {
		this.attentionNickName = attentionNickName;
	}
	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAttentionNickName(){
		return MemberLogRemarkUtils.getNickName(remark) ;
	}  
	public Long getAttentionMemberId(){
		return MemberLogRemarkUtils.getMemberId(remark) ;
	}
    
}
 