package com.aicai.jcob.tjplan.common.domain.result;  

import java.io.Serializable;
import java.util.Date;

/** 
 * @author jing.ming
 * @version 创建时间：2016年3月4日 下午2:38:56 
 * 推荐单详细信息 
 */
public class LookPlanDetailResult implements Serializable{

	private static final long serialVersionUID = -7390084517256359577L;
	private String matchNo ; //赛事编号
	private String matchName ;// 赛事类型
	private String matchStaus ; //比赛状态
	private Date matchTime ; //比赛时间
	private String matchVS ; //比赛对阵
	private String pubPanKou ;//发布时盘口(平手)
	private String pubSp ;//主胜：0.90           客胜：0.90
    private String tjResult ;// 皇马胜
    private String openResult ;//待开奖(XX胜)
    private String winStatus ;//中奖状态(待开奖)
    private String wholeScore ;//完场比分
    
    
	public String getWholeScore() {
		return wholeScore;
	}
	public void setWholeScore(String wholeScore) {
		this.wholeScore = wholeScore;
	}
	public String getMatchNo() {
		return matchNo;
	}
	public String getMatchName() {
		return matchName;
	}
	public String getMatchStaus() {
		return matchStaus;
	}
	public Date getMatchTime() {
		return matchTime;
	}
	public String getMatchVS() {
		return matchVS;
	}
	public String getPubPanKou() {
		return pubPanKou;
	}
	public String getPubSp() {
		return pubSp;
	}
	public String getTjResult() {
		return tjResult;
	}
	public String getOpenResult() {
		return openResult;
	}
	public String getWinStatus() {
		return winStatus;
	}
	public void setMatchNo(String matchNo) {
		this.matchNo = matchNo;
	}
	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}
	public void setMatchStaus(String matchStaus) {
		this.matchStaus = matchStaus;
	}
	public void setMatchTime(Date matchTime) {
		this.matchTime = matchTime;
	}
	public void setMatchVS(String matchVS) {
		this.matchVS = matchVS;
	}
	public void setPubPanKou(String pubPanKou) {
		this.pubPanKou = pubPanKou;
	}
	public void setPubSp(String pubSp) {
		this.pubSp = pubSp;
	}
	public void setTjResult(String tjResult) {
		this.tjResult = tjResult;
	}
	public void setOpenResult(String openResult) {
		this.openResult = openResult;
	}
	public void setWinStatus(String winStatus) {
		this.winStatus = winStatus;
	}
    
}
 