package com.aicai.jcob.tjplan.common.domain;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.List;

import com.aicai.jcob.common.utils.DateUtil;
import com.aicai.jcob.game.common.domain.constant.GameType;
import com.aicai.jcob.tjexpert.common.domain.constant.TjExpertLevel;
import com.aicai.jcob.tjplan.common.domain.constant.TjPlanLeagueTypeBit;
import com.aicai.jcob.tjplan.common.domain.constant.TjPlanOpenStatus;
import com.aicai.jcob.tjplan.common.domain.constant.TjPlanShowStatus;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceLeagueType;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceStatus;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceType;
import com.alibaba.fastjson.JSONObject;

public class TjPlan implements Serializable{
	
	public TjPlan(){
		
	}

	public TjPlan(TjRaceType tjRaceType, GameType gameType,
			Long memberId, TjExpertLevel expertLevel, BigDecimal amount,
			List<TjRaceLeagueType> leagueTypeList, String title, String describe) {
		this.raceType = tjRaceType.getIndex();
		this.gameId = gameType.getIndex();
		this.memberId = memberId;
		this.expertLevel = expertLevel.getIndex();
		this.amount = amount;
		this.leagueTypeList = leagueTypeList;
		this.titile = title;
		this.describe = describe;
	
	}
    /**
	 * 
	 */
	private static final long serialVersionUID = 4935686458850086168L;

	/**  */
    private Long id;

    /** 对应tj_race的race_type赛事类别：1竞足赛事，2竞篮赛事，3北单赛事 */
    private Integer raceType = 0;

    /** 彩种：单关，亚盘，大小球 */
    private Integer gameId = 0;

    /**  */
    private Long memberId = 0L;

    /** 专家级别 */
    private Integer expertLevel = 0;

    /** 免费/收费购买人数统计 */
    private Integer lookerCount = 0;

    /** 查看方案收费金额 */
    private BigDecimal amount = BigDecimal.valueOf(0);

    /** 命中场次 */
    private Float winRaceCount = 0f;

    /** TjPlanLeagueTypeBit 内部通过tjraceLeagueType转换
     * 联赛类别:bit二进制未按位标识
第一位：欧冠，第二位英超，第三位：西甲，第四位：意甲，第五位：德甲，第六位：法甲，第7位：荷甲，第八位：葡超 */
    private Long leagueTypeBit = 0L;
    
    private List<TjRaceLeagueType> leagueTypeList;

    /** 最早比赛赛事id */
    private Long minRaceId;

    /** 最晚比赛赛事id */
    private Long maxRaceId;

    /** 赛事id列表格式：1,2,3,4,5  (赛事id之间用逗号分割) */
    private String raceIdList;

    /** 方案状态:0方案赛事未开赛，方案赛事比赛中，方案赛事比赛完毕 */
    private Integer raceStatus = TjRaceStatus.not_match.getIndex();

    /** 开奖状态：0未开，1已开 */
    private Integer openStatus = TjPlanOpenStatus.not_open;

    /** 是否删除：0有效，1删除 */
    private Integer isDel = 0;

    /** 方案标题 */
    private String titile;

    /** 方案描述 */
    private String describe;

    /** json格式扩展用 */
    private String features = "{}";

    /**  */
    private Long flagBit = 0L;

    /** 创建时间 */
    private Calendar createTime = Calendar.getInstance();

    /**  */
    private Calendar updateTime = Calendar.getInstance();

    /** 排序字段 */
    private BigDecimal sortNo = BigDecimal.valueOf(0);
    
    private Integer clientType = 0 ;  //方案发布终端:0 android,1 ios ,2 h5,3web
    
    /**渠道**/
    private Integer channel = 1;

    /** 最近比赛赛事的开赛时间 */
    private Calendar minRaceMatchTime;
    
    private List<TjPlanItem> tjPlanItemList = null;
    

    /** 是否显示：0不显示，1显示 */
    private Integer isShow = TjPlanShowStatus.show.getIndex();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRaceType() {
        return raceType;
    }

    public void setRaceType(Integer raceType) {
        this.raceType = raceType;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getExpertLevel() {
        return expertLevel;
    }

    public void setExpertLevel(Integer expertLevel) {
        this.expertLevel = expertLevel;
    }

    public Integer getLookerCount() {
        return lookerCount;
    }

    public void setLookerCount(Integer lookerCount) {
        this.lookerCount = lookerCount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Float getWinRaceCount() {
        return winRaceCount;
    }

    public void setWinRaceCount(Float winRaceCount) {
        this.winRaceCount = winRaceCount;
    }

    public Long getLeagueTypeBit() {
    	List<Long> leagueTypeBitList = TjPlanLeagueTypeBit.leagueTypeToLeagueTypeBit(getLeagueTypeList());
    	if (leagueTypeBitList != null && leagueTypeBitList.size() > 0) {
			for (Long leagueBit : leagueTypeBitList) {
				addLeagueTypeBit(leagueBit);
			}
		}
        return leagueTypeBit;
    }

    public void setLeagueTypeBit(Long leagueTypeBit) {
        this.leagueTypeBit = leagueTypeBit;
    }

    public Long getMinRaceId() {
        return minRaceId;
    }

    public void setMinRaceId(Long minRaceId) {
        this.minRaceId = minRaceId;
    }

    public Long getMaxRaceId() {
        return maxRaceId;
    }

    public void setMaxRaceId(Long maxRaceId) {
        this.maxRaceId = maxRaceId;
    }

    public String getRaceIdList() {
        return raceIdList;
    }

    public void setRaceIdList(String raceIdList) {
        this.raceIdList = raceIdList == null ? null : raceIdList.trim();
    }

    public Integer getRaceStatus() {
        return raceStatus;
    }

    public void setRaceStatus(Integer raceStatus) {
        this.raceStatus = raceStatus;
    }

    public Integer getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(Integer openStatus) {
        this.openStatus = openStatus;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile == null ? null : titile.trim();
    }

    public String getDescribe() {
//    	try {
//			this.describe = URLDecoder.decode(describe, "utf-8");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
        return describe;
    }

    public String getDescribeToDb(){
//    	 try {
// 			this.describe = URLEncoder.encode(describe, "utf-8");
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 		}
    	return describe;
    }
    public void setDescribe(String describe) {
        this.describe = describe == null ? "" : describe.trim();
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

    public BigDecimal getSortNo() {
        return sortNo;
    }

    public void setSortNo(BigDecimal sortNo) {
        this.sortNo = sortNo;
    }

    public Calendar getMinRaceMatchTime() {
        return minRaceMatchTime;
    }

    public void setMinRaceMatchTime(Calendar minRaceMatchTime) {
        this.minRaceMatchTime = minRaceMatchTime;
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
    /**
     * 添加标记
     * @param flagBit
     */
    public void addFlagBit(Long flagBit) {
        this.flagBit |= flagBit.longValue();
    }
    /**
     * 添加联赛标记
     * @param leagueTypeBit
     */
    public void addLeagueTypeBit(Long leagueTypeBit) {
    	this.leagueTypeBit |= leagueTypeBit.longValue();
    }
    
    public String getTjPlanNo(){
    	//格式：时间 + id
    	String no = null;
    	if (createTime != null && id != null) {
    		no = DateUtil.dateToString(createTime.getTime(),"yyyyMMdd")+id;
		}
    	return no;
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

	public List<TjRaceLeagueType> getLeagueTypeList() {
		return leagueTypeList;
	}

	public void setLeagueTypeList(List<TjRaceLeagueType> leagueTypeList) {
		this.leagueTypeList = leagueTypeList;
	}

	public List<TjPlanItem> getTjPlanItemList() {
		return tjPlanItemList;
	}

	public void setTjPlanItemList(List<TjPlanItem> tjPlanItemList) {
		this.tjPlanItemList = tjPlanItemList;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		if(isShow == null){
			this.isShow =  TjPlanShowStatus.show.getIndex();
		}
		this.isShow = isShow;
	}
	
}