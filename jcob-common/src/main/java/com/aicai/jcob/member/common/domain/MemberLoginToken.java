package com.aicai.jcob.member.common.domain;

import java.io.Serializable;
import java.util.Calendar;

import com.aicai.jcob.member.common.domain.constant.MemberLoginTokenStatus;
import com.aicai.jcob.member.common.domain.constant.MemberLoginType;
import com.alibaba.fastjson.JSONObject;
/**
 * 用户持久化登录记录保存
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月22日
 */
public class MemberLoginToken implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3359417289557471898L;

	/**  */
    private Long id;

    /**  */
    private Long memberId;

    /** 机器号 */
    private String machineId;

    /** 登录类型 */
    private Integer loginType = MemberLoginType.login_phone.getIndex();

    /**用户登录token 
     * 由（memberId,machineId）决定。
     * 在注册时，用其他方式登录时，提供了machineId就要产生，用memberId＋machineId+登录时yyMMdd 24hh:mi:ss 的md5产生
     * */
    private String token;

    /** 状态：0无效，1有效 */
    private Integer status = MemberLoginTokenStatus.valid;

    /**  */
    private Calendar createTime = Calendar.getInstance();

    /**  */
    private Calendar updateTime = Calendar.getInstance();

    /**  */
    private String features = "{}";

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

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId == null ? null : machineId.trim();
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
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

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features == null ? "{}" : features.trim();
    }
    
	public void setupFeature(String columnName, String value) {
		JSONObject jsonO=JSONObject.parseObject(features);
		jsonO.put(columnName, value);
		features=jsonO.toJSONString();
	}
	public void setupFeature(String columnName, Object value) {
		JSONObject jsonO=JSONObject.parseObject(features);
		jsonO.put(columnName, value);
		features=jsonO.toJSONString();
	}
	public void removeFeature(String columnName) {
		JSONObject jsonO=JSONObject.parseObject(features);
		jsonO.remove(columnName);
		features=jsonO.toJSONString();
	}
	public String getFeature(String columnName) {
		JSONObject jsonO=JSONObject.parseObject(features);
		return jsonO.getString(columnName);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getFeature(String columnName, Class<T> clz) {
		JSONObject jsonO=JSONObject.parseObject(features);
		return (T)jsonO.get(columnName);
	}
}