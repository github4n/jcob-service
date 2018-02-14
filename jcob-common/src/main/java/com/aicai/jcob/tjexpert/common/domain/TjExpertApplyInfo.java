package com.aicai.jcob.tjexpert.common.domain;  

import java.io.Serializable;
import java.util.Calendar;
import com.aicai.jcob.tjexpert.common.domain.constant.TjExpertApplyCheckStatus;

/** 
 * @author jing.ming
 * @version 创建时间：2016年1月26日 下午2:16:54 
 * 专家申请类
 */
public class TjExpertApplyInfo implements Serializable {

	private static final long serialVersionUID = -2189697869627963410L;

	private Long id;
	private Long memberId ;

	/**审核状态：0待审核，1审核通过，2审核未通过*/
	private Integer checkStatus = TjExpertApplyCheckStatus.wait_check.getIndex();
	
	/**申请理由*/
	private String applyReason ;
	
	/**战绩图片地址,多张图片用逗号分隔*/
	private String winImgPath ; 
	
	/**审核不通过原因*/
	private String cancelReason ; 
	/**  */
    private Calendar createTime ;
    
    /**  */
    private Calendar updateTime ;
    

	public Long getId() {
		return id;
	}

	public Long getMemberId() {
		return memberId;
	}


	public Integer getCheckStatus() {
		return checkStatus;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public String getWinImgPath() {
		return winImgPath;
	}

	public String getCancelReason() {
		return cancelReason;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	public void setWinImgPath(String winImgPath) {
		this.winImgPath = winImgPath;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public Calendar getCreateTime() {
		return createTime;
	}

	public Calendar getUpdateTime() {
		return updateTime;
	}

	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Calendar updateTime) {
		this.updateTime = updateTime;
	}

    
    
}
 