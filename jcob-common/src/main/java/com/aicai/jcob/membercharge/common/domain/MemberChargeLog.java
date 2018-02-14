package com.aicai.jcob.membercharge.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.aicai.jcob.common.constant.ClientType;
import com.aicai.jcob.common.utils.DateUtil;
import com.aicai.jcob.membercharge.common.domain.constant.ChargeChannel;
import com.aicai.jcob.membercharge.common.domain.constant.ChargeStatus;
import com.aicai.jcob.membercharge.common.domain.constant.ChargeWay;

public class MemberChargeLog implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4207003374780604460L;

	/**  */
    private Long id;

    /**  */
    private Long memberId;

    /** 第三方充值平台流水号 */
    private String thirdChargeNo;

    /** 充值金额元为单位 */
    private BigDecimal amount;
    
    /**
     * 现金换火眼比例
     */
    private BigDecimal cashTOHuoyanRatio = BigDecimal.valueOf(1);

    /**  */
    private Integer clientType = ClientType.android.getIndex();

    /**  */
    private Integer channel = 1;

    /** 充值状态:0失败，1成功 */
    private Integer status = ChargeStatus.fail.getIndex();

    /** 充值方式索引：1支付宝，2微信 */
    private Integer chargeWayIndex = ChargeWay.zfb_sdk.getIndex();

    /** 充值渠道平台:1支付宝，2微信 */
    private Integer chargeChannelIndex = ChargeChannel.zfb_sdk.getIndex();

    /** 订单创建时间 */
    private Calendar requestTime = Calendar.getInstance();

    /** 第三方响应时间 */
    private Calendar responseTime;

    /** 手续费 */
    private BigDecimal handingCost = new BigDecimal(0);

    /** 前端通知地址 */
    private String notifyUrl;

    /** 前端页面回调地址 */
    private String returnUrl;

    /** 银行标识 */
    private String bankCode;

    private String bankCard;
    
    private Integer payType;
    
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

    public String getThirdChargeNo() {
        return thirdChargeNo;
    }

    public void setThirdChargeNo(String thirdChargeNo) {
        this.thirdChargeNo = thirdChargeNo == null ? null : thirdChargeNo.trim();
    }

   

    public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setHandingCost(BigDecimal handingCost) {
		this.handingCost = handingCost;
	}

	public BigDecimal getHandingCost() {
		return handingCost;
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

    public Integer getChargeWayIndex() {
        return chargeWayIndex;
    }

    public void setChargeWayIndex(Integer chargeWayIndex) {
        this.chargeWayIndex = chargeWayIndex;
    }

    public Integer getChargeChannelIndex() {
        return chargeChannelIndex;
    }

    public void setChargeChannelIndex(Integer chargeChannelIndex) {
        this.chargeChannelIndex = chargeChannelIndex;
    }

    public Calendar getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Calendar requestTime) {
        this.requestTime = requestTime;
    }

    public Calendar getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Calendar responseTime) {
        this.responseTime = responseTime;
    }


    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl == null ? null : returnUrl.trim();
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }
    
    public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public BigDecimal getCashTOHuoyanRatio() {
		return cashTOHuoyanRatio;
	}

	public void setCashTOHuoyanRatio(BigDecimal cashTOHuoyanRatio) {
		this.cashTOHuoyanRatio = cashTOHuoyanRatio;
	}
	
	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getChargeNo(){
    	//规则  创建时间  yyyyMMddHHmiss + id
		String no = null;
    	if (requestTime != null && id != null) {
    		no = DateUtil.dateToString(requestTime.getTime(),"yyyyMMdd")+id;
		}
    	return no;
    }
	
}