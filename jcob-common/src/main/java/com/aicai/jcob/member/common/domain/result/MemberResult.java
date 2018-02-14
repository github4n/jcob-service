package com.aicai.jcob.member.common.domain.result;

import java.io.Serializable;
import java.util.Calendar;

import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.member.common.domain.constant.MemberFlagBit;

public class MemberResult implements Serializable {

	private static final long serialVersionUID = -698084276274341231L;

	/**  */
    private Long memberId;

    /** 会员号：默认jcob_id
 	其他表不存这个号 */
    private String no;

    /** 密文密码 */
    private String password;

    /** 昵称,可以改 */
    private String nickName;

    /** 电话,可以改 */
    private String phone;

    /** 用户头像路径 */
    private String icon;

    /** 邮件 */
    private String email;

    /** 客户端类型: 0 android,1 ios ,2 h5,3web */
    private Integer clientType;

    /** 渠道来源 没有渠道暂时默认 1 */
    private Integer channel;

    /** 用户状态0无效，1有效 */
    private Integer status;

    /** 身份证 */
    private String certNo;

    /** 真实姓名 */
    private String realName;

    /** 证件类型:0身份证，1军官证 */
    private Integer certType;

    /**  */
    private String clientVersion;

    /** 注册类型：1本站手机号注册，2本站邮件注册，3第三方登录注册 */
    private Integer registerType;

    /** 用户多种属性二进制位标识 */
    private Long flagBit;

    /** 注册创建时间 */
    private Calendar createTime;

    /** 信息修改时间 */
    private Calendar updateTime;

    /**  */
    private Calendar lastLoginTime;
	
    /** 等级：0普通用户，1实验室专家，2正式专家,3带v的正式专家 */
    private Integer level;

    public MemberResult(){}
    
    public MemberResult(Member member){
    	this.setMemberId(member.getId());
    	this.setNickName(member.getNickName());
    	this.setStatus(member.getStatus());
    	this.setClientType(member.getClientType());
    	this.setRegisterType(member.getRegisterType());
    	this.setCreateTime(member.getCreateTime());
    	this.setPhone(member.getPhone());
    	this.setFlagBit(member.getFlagBit());
    	this.setRealName(member.getRealName());
    	this.setCertNo(member.getCertNo());
    	this.setIcon(member.getIcon());
    }
    
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getCertType() {
		return certType;
	}

	public void setCertType(Integer certType) {
		this.certType = certType;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public Integer getRegisterType() {
		return registerType;
	}

	public void setRegisterType(Integer registerType) {
		this.registerType = registerType;
	}

	public Long getFlagBit() {
		return flagBit;
	}

	public void setFlagBit(Long flagBit) {
		this.flagBit = flagBit;
	}

	public Calendar getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	public Calendar getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Calendar updateTime) {
		this.updateTime = updateTime;
	}

	public Calendar getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Calendar lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	} 
    
    public boolean isPhoneValid() {
    	return MemberFlagBit.isExistFlagBit(MemberFlagBit.phone_valid, this.getFlagBit());
    }
}
