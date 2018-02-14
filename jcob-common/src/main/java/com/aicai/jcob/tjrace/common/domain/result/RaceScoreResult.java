package com.aicai.jcob.tjrace.common.domain.result;

import java.io.Serializable;

public class RaceScoreResult implements Serializable {
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**全场比分*/
    private String wholeScore;
    private String uniqueMatchNo;
    
    
    
	public String getWholeScore() {
		return wholeScore;
	}
	public void setWholeScore(String wholeScore) {
		this.wholeScore = wholeScore;
	}
	public String getUniqueMatchNo() {
		return uniqueMatchNo;
	}
	public void setUniqueMatchNo(String uniqueMatchNo) {
		this.uniqueMatchNo = uniqueMatchNo;
	}
    
    
}
