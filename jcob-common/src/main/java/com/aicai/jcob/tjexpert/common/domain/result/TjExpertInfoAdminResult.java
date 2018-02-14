package com.aicai.jcob.tjexpert.common.domain.result;  

import java.io.Serializable;
import java.util.List;

import com.aicai.jcob.tjexpert.common.domain.TjExpertLevelLog;

/** 
 * @author jing.ming
 * @version 创建时间：2016年3月7日 下午4:42:48 
 * 程序的简单说明 
 */
public class TjExpertInfoAdminResult implements Serializable{

	private static final long serialVersionUID = 6607800095074698137L;

	/**  */
    private Long memberId;

    private String nickName ;
    /** 等级：0普通用户，1实验室专家，2正式专家,3带v的正式专家 */
    private Integer level ;
    /**专家成长记录list*/
	private List<TjExpertLevelLog>  tjExpertLevelLogList ;
	public Long getMemberId() {
		return memberId;
	}
	public String getNickName() {
		return nickName;
	}
	public Integer getLevel() {
		return level;
	}
	public List<TjExpertLevelLog> getTjExpertLevelLogList() {
		return tjExpertLevelLogList;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public void setTjExpertLevelLogList(List<TjExpertLevelLog> tjExpertLevelLogList) {
		this.tjExpertLevelLogList = tjExpertLevelLogList;
	}
	
	
}
 