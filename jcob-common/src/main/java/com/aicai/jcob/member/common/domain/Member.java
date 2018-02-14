package com.aicai.jcob.member.common.domain;

import java.io.Serializable;
import java.util.Calendar;

import com.aicai.jcob.common.constant.ClientType;
import com.aicai.jcob.member.common.domain.constant.CertType;
import com.aicai.jcob.member.common.domain.constant.MemberFlagBit;
import com.aicai.jcob.member.common.domain.constant.MemberStatus;
import com.aicai.jcob.member.common.domain.constant.PasswordStrength;
import com.aicai.jcob.member.common.domain.constant.RegisterType;
/**
 * 用户
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月22日
 */
public class Member implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -698084276274341231L;

	/**  */
    private Long id;

    /** 会员号：默认jcob_id

 其他表不存这个号 */
    private String no;

    /** 密文密码 */
    private String password;
    /**
     * 明文密码强度
     */
    private Integer passwordStrength = PasswordStrength.weak.getIndex();
    
    /** 昵称,可以改 */
    private String nickName;

    /** 电话,可以改 */
    private String phone;

    /** 用户头像路径 */
    private String icon;

    /** 邮件 */
    private String email;

    /** 客户端类型: 0 android,1 ios ,2 h5,3web */
    private Integer clientType = ClientType.android.getIndex();

    /** 渠道来源 没有渠道暂时默认 1 */
    private Integer channel = 1;

    /** 用户状态0无效，1有效 */
    private Integer status = MemberStatus.valid;

    /** 身份证 */
    private String certNo;

    /** 真实姓名 */
    private String realName;

    /** 证件类型:0身份证，1军官证 */
    private Integer certType = CertType.identity_card.getIndex();

    /**  */
    private String clientVersion;

    /** 注册类型：1本站手机号注册，2本站邮件注册，3第三方登录注册 */
    private Integer registerType = RegisterType.phone_register.getIndex();

    /** 用户多种属性二进制位标识 */
    private Long flagBit = MemberFlagBit.defualt;

    /** 注册创建时间 */
    private Calendar createTime =  Calendar.getInstance();

    /** 信息修改时间 */
    private Calendar updateTime = Calendar.getInstance();

    /**  */
    private Calendar lastLoginTime =  Calendar.getInstance();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
    
    public Integer getPasswordStrength() {
		return passwordStrength;
	}

	public void setPasswordStrength(Integer passwordStrength) {
		this.passwordStrength = passwordStrength;
	}

	public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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
        this.certNo = certNo == null ? null : certNo.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
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
        this.clientVersion = clientVersion == null ? null : clientVersion.trim();
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
    /**
     * 添加用户标记
     * @param flagBit
     */
    public void addFlagBit(Long flagBit) {
        this.flagBit |= flagBit.longValue();
    }
}