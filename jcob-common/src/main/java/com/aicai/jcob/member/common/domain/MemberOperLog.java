package com.aicai.jcob.member.common.domain;

import java.io.Serializable;
import java.util.Calendar;

import com.aicai.jcob.common.constant.ClientType;
import com.aicai.jcob.member.common.domain.constant.OperType;
/**
 * 用户操作流水
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月22日
 */
public class MemberOperLog implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5926191191248291415L;

	/**  */
    private Long id;

    /**  */
    private Long memberId;

    /** 操作类型: 0 注册，1登录，2发布推荐方案，3查看专家方案，4关注专家，5取消专家关注，6充值，7提款，8修改用户信息 */
    private Integer operType = OperType.login.getIndex();

    /** 操作平台 */
    private Integer clientType = ClientType.android.getIndex();

    /** 渠道 */
    private Integer channel = 1;

    /** 用户ip */
    private String userIp;

    /** 接口服务器ip */
    private String frontServerIp;

    /**  */
    private String backServerIp;

    /** 备注 */
    private String remark;

    /**  */
    private Calendar createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
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

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp == null ? null : userIp.trim();
    }

    public String getFrontServerIp() {
        return frontServerIp;
    }

    public void setFrontServerIp(String frontServerIp) {
        this.frontServerIp = frontServerIp == null ? null : frontServerIp.trim();
    }

    public String getBackServerIp() {
        return backServerIp;
    }

    public void setBackServerIp(String backServerIp) {
        this.backServerIp = backServerIp == null ? null : backServerIp.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }
}