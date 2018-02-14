package com.aicai.jcob.member.common.domain;

import java.io.Serializable;
import java.util.Calendar;

import com.aicai.jcob.member.common.domain.constant.MemberLoginStatus;
import com.aicai.jcob.member.common.domain.constant.MemberLoginType;
/**
 * 用户登录方式
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月22日
 */
public class MemberLogin implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4846126413486152831L;

	/**  */
    private Integer id;

    /**  */
    private Long memberId;

    private Member member;
    
    private MemberLoginToken memberLoginToken;
    
    /** 登录类型：1手机号登录,2邮箱登录,3第三方id登录 */
    private Integer loginType = MemberLoginType.login_phone.getIndex();

    /** 登录号：本站的就是手机号，第三方的就是第三方id */
    private String loginId;

    /** 状态：0无效，1有效 */
    private Integer status = MemberLoginStatus.valid;

    /**  */
    private Calendar createTime = Calendar.getInstance();

    /**  */
    private Calendar updateTime = Calendar.getInstance();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    
    public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId == null ? null : loginId.trim();
    }
    
    public MemberLoginToken getMemberLoginToken() {
		return memberLoginToken;
	}

	public void setMemberLoginToken(MemberLoginToken memberLoginToken) {
		this.memberLoginToken = memberLoginToken;
	}

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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