package com.aicai.jcob.tjrace.common.domain.result;

import com.aicai.jcob.tjrace.common.domain.TjRace;

/**
 * 赛事盘口信息，对TjRace的再度包装
 *
 */
public class RaceHandicap extends TjRace {

	private static final long serialVersionUID = -7430711280165656982L;

	private Integer raceType;

	// 单关胜平负开售
	private boolean isSpfSale;

	// 单关让球胜平负开售
	private boolean isRqspfSale;
	
	// 过关胜平负开售
	private boolean isGgSpfSale;

	// 让球数0为不让球 >0为 主队让球 <0 为客队让球
	private String concede;

	// 亚盘盘口
	private String yp;

	// 大小球盘口
	private String dxq;

	// 胜平负sp 例如：1.6,1.8,1.9
	private String spfSp;

	// 让球胜平负sp 例如：1.6,1.8,1.9
	private String rqsqfSp;

	// 亚盘sp 例如：1.6,1.8
	private String ypSp;

	// 大小球sp 例如：1.6,1.8
	private String dxqSp;

	public Integer getRaceType() {
		return raceType;
	}

	public void setRaceType(Integer raceType) {
		this.raceType = raceType;
	}

	public boolean isSpfSale() {
		return isSpfSale;
	}

	public void setSpfSale(boolean isSpfSale) {
		this.isSpfSale = isSpfSale;
	}

	public boolean isRqspfSale() {
		return isRqspfSale;
	}

	public void setRqspfSale(boolean isRqspfSale) {
		this.isRqspfSale = isRqspfSale;
	}

	public String getConcede() {
		return concede;
	}

	public void setConcede(String concede) {
		this.concede = concede;
	}

	public String getSpfSp() {
		return spfSp;
	}

	public void setSpfSp(String spfSp) {
		this.spfSp = spfSp;
	}

	public String getRqsqfSp() {
		return rqsqfSp;
	}

	public void setRqsqfSp(String rqsqfSp) {
		this.rqsqfSp = rqsqfSp;
	}

	public String getYpSp() {
		return ypSp;
	}

	public void setYpSp(String ypSp) {
		this.ypSp = ypSp;
	}

	public String getDxqSp() {
		return dxqSp;
	}

	public void setDxqSp(String dxqSp) {
		this.dxqSp = dxqSp;
	}

	public String getYp() {
		return yp;
	}

	public void setYp(String yp) {
		this.yp = yp;
	}

	public String getDxq() {
		return dxq;
	}

	public void setDxq(String dxq) {
		this.dxq = dxq;
	}

	public boolean isGgSpfSale() {
		return isGgSpfSale;
	}

	public void setGgSpfSale(boolean isGgSpfSale) {
		this.isGgSpfSale = isGgSpfSale;
	}

	@Override
	public String toString() {
		return "RaceHandicap [raceType=" + raceType + ", isSpfSale=" + isSpfSale + ", isRqspfSale=" + isRqspfSale + ", concede="
				+ concede + ", yp=" + yp + ", dxq=" + dxq + ", spfSp=" + spfSp + ", rqsqfSp=" + rqsqfSp + ", ypSp=" + ypSp
				+ ", dxqSp=" + dxqSp + "]";
	}

}
