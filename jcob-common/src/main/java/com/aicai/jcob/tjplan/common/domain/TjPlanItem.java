package com.aicai.jcob.tjplan.common.domain;

import java.io.Serializable;
import java.util.Calendar;

import com.aicai.jcob.game.common.domain.constant.GameType;
import com.aicai.jcob.tjplan.common.domain.constant.TjPlanItemWinStatus;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceType;
import com.alibaba.fastjson.JSONObject;

public class TjPlanItem implements Serializable{
	private static final long serialVersionUID = 1897833187271726930L;
	
	public TjPlanItem(){
		
	}

	public TjPlanItem(GameType gameType,TjRaceType raceType,String content,Long raceId){
		this.gameId = gameType.getIndex();
		this.raceType = raceType.getIndex();
		this.content = content;
		this.raceId = raceId;
	}
	/**  */
    private Long id;

    /**  */
    private Long planId;

    /**  */
    private Integer gameId;

    /** 赛事类型 */
    private Integer raceType;

    /** 方案内容一场赛事一条记录 格式：    场次（胜-sp,平-sp） */
    private String content;

    /** 0未开，1未中，2已中 */
    private Integer winStatus = TjPlanItemWinStatus.not_open.getIndex();
    
    /** json格式扩展用 */
    private String features = "{}";

    /**  */
    private Calendar createTime = Calendar.getInstance();

    /**  */
    private Calendar updateTime = Calendar.getInstance();
    
    /**赛事id TjRace.id**/
    private Long raceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getRaceType() {
        return raceType;
    }

    public void setRaceType(Integer raceType) {
        this.raceType = raceType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getWinStatus() {
        return winStatus;
    }

    public void setWinStatus(Integer winStatus) {
        this.winStatus = winStatus;
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

	public Long getRaceId() {
		return raceId;
	}

	public void setRaceId(Long raceId) {
		this.raceId = raceId;
	}
    
	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
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
}