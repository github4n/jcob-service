package com.aicai.jcob.tjexpert.common.domain.result;  

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.util.StringUtils;


/** 
 * @author jing.ming
 * @version 创建时间：2016年3月8日 上午9:03:35 
 * 程序的简单说明 
 */
public class TjExpertApplyAdminResult implements Serializable {

	private static final long serialVersionUID = -5880824095513631178L;
    private Long id ;
	/**用户id*/
	private Long memberId ;
	/**手机号*/
	private String phone ;
	/**昵称*/
	private String nickName ;
	
	private String certNo ;
	private String realName ;
	/**专家审核状态*/
	private Integer checkStatus;
	
	/**申请理由*/
	private String applyReason ;
	
	/**战绩图片地址,多张图片用逗号分隔*/
	private String winImgPath ; 
	
	/**审核不通过原因*/
	private String cancelReason ; 
	/**  */
    private Calendar createTime ;
	public Long getId() {
		return id;
	}
	public Long getMemberId() {
		return memberId;
	}
	public String getPhone() {
		return phone;
	}
	public String getNickName() {
		return nickName;
	}
	public String getCertNo() {
		return certNo;
	}
	public String getRealName() {
		return realName;
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
	public Calendar getCreateTime() {
		return createTime;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}
    
    public List<String> pathImgList(String winImgPath){
    	if(StringUtils.isEmpty(winImgPath)){
    		return  null ;
    	}
    	List<String> imgList = new ArrayList<String>() ;
    	String[] imgArr= winImgPath.split(",") ;
    	if(imgArr.length==0){
    		return null ;
    	}
    	for(String str:imgArr){
    		imgList.add(str) ;
    	}
    	return imgList ;
    }

}
 