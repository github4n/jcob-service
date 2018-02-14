package com.aicai.jcob.memberwallet.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.aicai.jcob.common.constant.ClientType;
import com.aicai.jcob.common.utils.MD5;
import com.aicai.jcob.memberwallet.common.domain.constant.BizType;
import com.aicai.jcob.memberwallet.common.domain.constant.WalletOpType;
import com.alibaba.fastjson.JSONObject;

public class MemberWalletLog implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 3317469405246642352L;

	/**  */
    private Long id;

    /**  */
    private Long memberId;

    /** 业务id */
    private Long bizId;

    /** 业务编号(充值流水号，查看推荐的方案号) */
    private String bizNo;

    /** 业务类型：1查看付费推荐方案，2充值，3提款 */
    private Integer bizType = BizType.look_plan.getIndex();

    /** 钱包操作类型：1扣款，2加钱 */
    private Integer walletOpType = WalletOpType.add_money.getIndex();

    /** 当前发生金额 */
    private BigDecimal happenAmount = BigDecimal.valueOf(0);


    /** 期末可用金额 */
    private BigDecimal endAbleBalance = BigDecimal.valueOf(0);

    /** 期末已冻结余额 */
    private BigDecimal endFreezedBalance = BigDecimal.valueOf(0);

    /** 流水完整性校验md5() */
    private String verifyMd5;

    /** json类型，用来记录扩展信息 */
    private String features = "{}";

    /**  */
    private Long flagBit = 0L;

    /**  */
    private Calendar createTime = Calendar.getInstance();

    /**  */
    private Calendar updateTime = Calendar.getInstance();

    private Integer clientType = ClientType.android.getIndex();
    private Integer channel = 1;
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

    public Long getBizId() {
        return bizId;
    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo == null ? null : bizNo.trim();
    }

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public Integer getWalletOpType() {
        return walletOpType;
    }

    public void setWalletOpType(Integer walletOpType) {
        this.walletOpType = walletOpType;
    }

    public BigDecimal getHappenAmount() {
        return happenAmount;
    }

    public void setHappenAmount(BigDecimal happenAmount) {
        this.happenAmount = happenAmount;
    }

    public BigDecimal getEndAbleBalance() {
        return endAbleBalance;
    }

    public void setEndAbleBalance(BigDecimal endAbleBalance) {
        this.endAbleBalance = endAbleBalance;
    }

    public BigDecimal getEndFreezedBalance() {
        return endFreezedBalance;
    }

    public void setEndFreezedBalance(BigDecimal endFreezedBalance) {
        this.endFreezedBalance = endFreezedBalance;
    }

    public String getVerifyMd5() {
        return verifyMd5;
    }

    public void setVerifyMd5(String verifyMd5) {
        this.verifyMd5 = verifyMd5 == null ? null : verifyMd5.trim();
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features == null ? null : features.trim();
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
    
    /**
     * 添加标记
     * @param flagBit
     */
    public void addFlagBit(Long flagBit) {
        this.flagBit |= flagBit.longValue();
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
	
	public <T> T getFeature(String columnName, Class<T> clz) {
		JSONObject jsonO=JSONObject.parseObject(features);
		return (T)jsonO.get(columnName);
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
	 public String getCalculateVerifyMd5(){
	    	return MD5.md5Encode("md5"+memberId.toString() + happenAmount.toString() + endAbleBalance.toString() + endFreezedBalance.toString() );
	    }
	
}