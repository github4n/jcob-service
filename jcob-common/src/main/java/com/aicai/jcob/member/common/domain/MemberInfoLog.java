package com.aicai.jcob.member.common.domain;

import java.io.Serializable;
import java.util.Calendar;

import com.aicai.jcob.member.common.domain.constant.MemberInfoType;
/**
 * 用户信息变更流水
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月22日
 */
public class MemberInfoLog implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2780664878574702565L;

	/**  */
    private Long id;

    /**  */
    private Long memberId;

    /** 状态：0无效，1有效 */
    private Integer status = 1;

    /** 信息类型:0：手机号，1：昵称，2：身份证，3：真实姓名 */
    private Integer infoType = MemberInfoType.nick_name.getIndex();

    /** 修改前的信息 */
    private String oldInfo;

    /** 修改后的信息 */
    private String newInfo;

    /**  */
    private Calendar createTime = Calendar.getInstance();

    /**  */
    private Calendar updateTime = Calendar.getInstance();

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getInfoType() {
        return infoType;
    }

    public void setInfoType(Integer infoType) {
        this.infoType = infoType;
    }

    public String getOldInfo() {
        return oldInfo;
    }

    public void setOldInfo(String oldInfo) {
        this.oldInfo = oldInfo == null ? null : oldInfo.trim();
    }

    public String getNewInfo() {
        return newInfo;
    }

    public void setNewInfo(String newInfo) {
        this.newInfo = newInfo == null ? null : newInfo.trim();
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
}