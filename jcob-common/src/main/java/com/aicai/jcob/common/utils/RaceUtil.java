package com.aicai.jcob.common.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.aicai.jcob.tjrace.common.domain.TjRace;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceLeagueType;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceStatus;
import com.aicai.jcob.tjrace.common.domain.result.RaceHandicap;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class RaceUtil {
	private static final Logger logger = LoggerFactory.getLogger(RaceUtil.class);
	private static final String RQSPF = "让球胜平负单关";
	private static final String SPF = "胜平负单关";
	private static final String SF = "胜负单关";

	private static final String SPF_GG = "胜平负过关";

	// private static final String RQSPF_GG = "让球胜平负过关";

	/**
	 * false 表示足彩单关未开
	 * 
	 * @param specialHint
	 * @return
	 */
	public static boolean isZcDgOpen(String specialHint) {
		if (StringUtils.isBlank(specialHint)) {
			return true;
		}
		if (specialHint.contains(SPF) || specialHint.contains(RQSPF)) {
			return false;
		}
		return true;
	}

	/**
	 * false 表示篮彩单关未开
	 * 
	 * @param specialHint
	 * @return
	 */
	public static boolean isLcDgOpen(String specialHint) {
		if (StringUtils.isBlank(specialHint)) {
			return true;
		} else if (specialHint.contains(SF)) {
			return false;
		}
		return true;
	}

	// 获得6个月前的日期
	public static Calendar getSixMonthBefore(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 6);
		return calendar;
	}

	// 获得6个月后的日期
	public static Calendar getSixMonthAfter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 6);
		return calendar;
	}

	public static List<String> getRaceIssue() {
		Calendar cal1 = getSixMonthBefore(new Date());
		Calendar cal2 = getSixMonthAfter(new Date());
		List<String> issues = new ArrayList<String>();
		for (long temp = cal1.getTimeInMillis(); temp < cal2.getTimeInMillis();) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(temp);

			int year = c.get(Calendar.YEAR);
			int year_week = c.get(Calendar.WEEK_OF_YEAR);

			int month = c.get(Calendar.MONTH);
			if (year_week == 1 && month == 11) {
				year = year + 1;
			}

			String yearStr = String.valueOf(year).substring(2);

			String year_week_str = String.valueOf(year_week);
			if (year_week_str.length() == 1) {
				year_week_str = "0" + year_week_str;
			}

			String issue = yearStr + year_week_str;
			issues.add(issue);
			temp = temp + (7 * 24 * 60 * 60 * 1000);
		}
		Collections.reverse(issues);
		return issues;
	}

	public static String getRaceCurrentIssue() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int year = c.get(Calendar.YEAR);

		int year_week = c.get(Calendar.WEEK_OF_YEAR);
		int month = c.get(Calendar.MONTH);
		if (year_week == 1 && month == 11) {
			year = year + 1;
		}
		String yearStr = String.valueOf(year).substring(2);
		String year_week_str = String.valueOf(year_week);
		if (year_week_str.length() == 1) {
			year_week_str = "0" + year_week_str;
		}
		String issue = yearStr + year_week_str;
		return issue;
	}

	/***
	 * 构建唯一对阵编号
	 * 
	 * 竞足 yyyymmdd(publish_time)+match_no 如 150801001 竞篮
	 * yyyymmdd(publish_time)+match_no 如 150801301
	 * 
	 * @return
	 */
	public static Long buildUniqueMatchNo(TjRace race) {
		String publishTimeStr = DateUtil.toYYMMDD(DateUtil.getCalenderByDate(race.getPublishTime()));
		String matchNo = String.valueOf(race.getMatchNo());
		matchNo = StringUtils.leftPad(matchNo, 3, "0");
		return Long.valueOf(publishTimeStr + matchNo);
	}

	/**
	 * 盘口标记:单关胜平负；单关让球胜平负；亚盘让球盘口;大小球盘口;串关胜平负让球数;串关胜平负 字符串格式：0;让球数;亚盘盘口;大小球盘口;
	 * 举例说明：_;-1;1/1.5;2/2.5;-1;0(单场胜平负没有开用"_"表示)
	 * 举例说明：0;_;1/1.5;2/2.5;-1;0(单场让球胜平负没有开用"_"表示，开了用"0"表示)
	 * 举例说明：0;_;1/1.5;2/2.5;0;0(串关让球数没有用"0"或者"_"表示)
	 * 举例说明：0;_;1/1.5;2/2.5;0;_(串关胜平负没有开用"_"表示)
	 * 
	 * SP：竞彩主胜SP,竞彩平SP,竞彩主负SP;亚盘主胜SP,亚盘主负SP;大小球主胜SP,大小球主负SP
	 * 字符串格式：1.25,0.9,1.26;_;1.25,0.9;1.25,0.9，如果让球胜平负没有开用"_"表示
	 * */
	public static RaceHandicap buildRaceHandicap(TjRace race) {
		String handicap = race.getHandicap();
		String sp = race.getSp();
		RaceHandicap raceHandicap = new RaceHandicap();
		raceHandicap.setRaceType(race.getRaceType());
		String[] handArray = handicap.split(";");
		String[] spArray = sp.split(";");
		// 胜平负SP
		if (handArray[0].equals("0")) {
			raceHandicap.setSpfSale(true);
			raceHandicap.setSpfSp(spArray[0]);
		} else {
			raceHandicap.setSpfSale(false);
		}
		// 让球胜平负SP
		if (handArray[1].equals("_")) {
			raceHandicap.setRqspfSale(false);
		} else {
			raceHandicap.setRqspfSale(true);
			raceHandicap.setConcede(handArray[1]);
			raceHandicap.setRqsqfSp(spArray[1]);
		}
		// 亚盘SP
		if (!handArray[2].equals("_")) {
			raceHandicap.setYpSp(spArray[2]);
			raceHandicap.setYp(handArray[2]);
		}
		// 大小球SP
		if (!handArray[3].equals("_")) {
			raceHandicap.setDxqSp(spArray[3]);
			raceHandicap.setDxq(handArray[3]);
		}
		// 让球数 兼容旧的格式
		if (handArray.length >= 5) {
			if (handArray[4].equals("_")) {
				raceHandicap.setConcede("0");
			} else {
				raceHandicap.setConcede(handArray[4]);
			}
			if (handArray.length == 6) {
				if (handArray[5].equals("0")) {
					raceHandicap.setGgSpfSale(true);
				} else {
					raceHandicap.setGgSpfSale(false);
				}
			}
		}
		BeanUtils.copyProperties(race, raceHandicap);
		return raceHandicap;
	}

	public static final String SPF_EN = "spf";
	public static final String RQSPF_EN = "rqspf";

	/**
	 * 获得单关状态 使用示范： <br/>
	 * Map<String, Boolean> map = RaceUtil.getDgStatus(tjRace.getHandicap())<br/>
	 * boolean isSpfOpen = map.get(RaceUtil.SPF_EN); true/false表示开/未开<br/>
	 * boolean isRqspfOpen = map.get(RaceUtil.RQSPF_EN);
	 * 
	 * @param handicap
	 * @return
	 */
	public static Map<String, Boolean> getDgStatus(String handicap) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		String[] handArray = handicap.split(";");
		// 胜平负
		if (handArray[0].equals("0")) {
			map.put(SPF_EN, true);
		} else {
			map.put(SPF_EN, false);
		}
		// 让球胜平负
		if (handArray[1].equals("_")) {
			map.put(RQSPF_EN, false);
		} else {
			map.put(RQSPF_EN, true);
		}
		return map;
	}

	/**
	 * 临时方法：将串关胜平负让球数置换handicap的第二位;串关胜平负置换handicap的第一位
	 */
	public static String formatHandicap(String handicap) {
		String[] handArray = handicap.split(";");
		int length = handArray.length;
		// 兼容旧的格式
		if (length == 5) {
			return handArray[0] + ";" + handArray[4] + ";" + handArray[2] + ";" + handArray[3];
		} else if (length == 6) {
			return handArray[5] + ";" + handArray[4] + ";" + handArray[2] + ";" + handArray[3];
		}
		return handicap;
	}

	/**
	 * 临时方法 配合formatHandicap使用 获得串关状态 使用示范： <br/>
	 * Map<String, Boolean> map = RaceUtil.getCgStatus(tjRace.getHandicap())<br/>
	 * boolean isSpfOpen = map.get(RaceUtil.SPF_EN); true/false表示开/未开<br/>
	 * boolean isRqspfOpen = map.get(RaceUtil.RQSPF_EN);
	 * 
	 * @param handicap
	 * @return
	 */
	public static Map<String, Boolean> getCgStatus(String handicap) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		String[] handArray = handicap.split(";");
		// 让球胜平负
		if (handArray[1].equals("0") || handArray[1].equals("_")) {
			map.put(RQSPF_EN, false);
		} else {
			map.put(RQSPF_EN, true);
		}
		// 胜平负
		if (handArray[0].equals("_")) {
			map.put(SPF_EN, false);
		} else {
			map.put(SPF_EN, true);
		}
		return map;
	}

	/**
	 * 判断亚盘大小球盘口是否存在
	 * 
	 * @param handicap
	 * @return
	 */
	public static boolean isYpDxqExist(String handicap) {
		String[] handArray = handicap.split(";");
		if (handArray[2].equals("_") || handArray[3].equals("_")) {
			return false;
		}
		return true;

	}

	/**
	 * 
	 * @param concede
	 * @param isSpfOpen
	 *            单关胜平负是否开
	 * @param isSpfGgOpen
	 *            串关胜平负是否开
	 * @return
	 */
	public static String getHandicapStr(String concede, boolean isSpfOpen, boolean isSpfGgOpen) {
		StringBuilder sb = new StringBuilder();
		if (isSpfOpen) {
			sb.append("0;");
		} else {
			sb.append("_;");
		}
		if (StringUtils.isBlank(concede) || concede.equals("0")) {
			sb.append("_;");
		} else {
			sb.append(concede).append(";");
		}
		sb.append("_;_;");
		// 处理串关让球
		if (StringUtils.isBlank(concede)) {
			sb.append("_;");
		} else {
			sb.append(concede).append(";");
		}
		if (isSpfGgOpen) {
			sb.append("0");
		} else {
			sb.append("_");
		}
		return sb.toString();
	}

	/**
	 * 构建Handicap字符串
	 * 
	 * @param concede
	 *            主队让球
	 */
	public static String getHandicapStr(String concede, String specialHint, String yp, String dxq) {
		StringBuilder sb = new StringBuilder();
		// 都开了
		if (StringUtils.isBlank(specialHint)) {
			sb.append("0;");
			if (StringUtils.isBlank(concede)) {
				sb.append("_;");
			} else {
				sb.append(concede).append(";");
			}
		} else {
			// 胜平负未开
			if (StringUtils.contains(specialHint, SPF)) {
				sb.append("_;");
			} else {
				sb.append("0;");
			}
			// 让球胜平负未开
			if (StringUtils.contains(specialHint, RQSPF)) {
				sb.append("_;");
			} else {
				sb.append(concede).append(";");
			}
		}
		// 亚盘
		if (StringUtils.isNotBlank(yp)) {
			sb.append(yp).append(";");
		} else {
			sb.append("_;");
		}
		// 大小球
		if (StringUtils.isNotBlank(dxq)) {
			sb.append(dxq).append(";");
		} else {
			sb.append("_;");
		}
		// 串关胜平负让球
		if (StringUtils.isBlank(concede)) {
			sb.append("_;");
		} else {
			sb.append(concede).append(";");
		}
		// 串关胜平负
		if (StringUtils.contains(specialHint, SPF_GG)) {
			sb.append("_");
		} else {
			sb.append("0");
		}
		return sb.toString();
	}

	public static String getSpStr(String spfSp, String rqSpfSp, String ypSp, String dxpSp) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isBlank(spfSp)) {
			sb.append("_");
		} else {
			String dGSpfSp = StringUtils.substringAfter(spfSp, ";");// 获得单关SP
			sb.append(dGSpfSp.replace("-", ","));
		}
		if (StringUtils.isBlank(rqSpfSp)) {
			sb.append(";_");
		} else {
			String dGRqSpfSp = StringUtils.substringAfter(rqSpfSp, ";");// 获得单关SP
			sb.append(";").append(dGRqSpfSp.replace("-", ","));
		}
		// 亚盘
		if (StringUtils.isNotBlank(ypSp)) {
			sb.append(";").append(ypSp);
		} else {
			sb.append(";_");
		}
		// 大小球
		if (StringUtils.isNotBlank(dxpSp)) {
			sb.append(";").append(dxpSp);
		} else {
			sb.append(";_");
		}
		return sb.toString();
	}

	/**
	 * 获得联赛类型
	 * 
	 * @param matchName
	 * @return
	 */
	public static Integer getLeagueTypeIndex(String matchName) {
		if (StringUtils.isBlank(matchName)) {
			return 0;
		}
		TjRaceLeagueType type = TjRaceLeagueType.valeOfByDesc(matchName);
		if (type == null) {
			return 0;
		}
		return type.getIndex();
	}

	public static String getWeekOfYear(Date publishTime) {
		int[] yearAndWeek = getYearAndWeekNumber(DateUtil.getCalenderByDate(publishTime));
		String weekOfYear = (yearAndWeek[0] + "").substring(2)
				+ ((yearAndWeek[1] < 10) ? "0" + yearAndWeek[1] : "" + yearAndWeek[1]);
		return weekOfYear; // 该期为当前年的第几周
	}

	/**
	 * 计算指定日期为一年中的第几周,为符合中国习惯:将周日所在的周数-1
	 * 
	 * @param dt
	 * @return, e.g. {2009, 1}
	 */
	public static int[] getYearAndWeekNumber(Calendar calendar) {

		Calendar c = Calendar.getInstance();
		c.setTime(calendar.getTime());

		int dayNum = getWeekDayNumber(c);
		int[] yearAndWeek = new int[2];
		if (c.get(Calendar.MONTH) == 11 && c.get(Calendar.WEEK_OF_YEAR) == 1) {
			yearAndWeek = new int[] { c.get(Calendar.YEAR) + 1,
					(dayNum == 7) ? (c.get(Calendar.WEEK_OF_YEAR) - 1) : c.get(Calendar.WEEK_OF_YEAR) };
		} else {
			yearAndWeek = new int[] { c.get(Calendar.YEAR),
					(dayNum == 7) ? (c.get(Calendar.WEEK_OF_YEAR) - 1) : c.get(Calendar.WEEK_OF_YEAR) };
		}
		if (dayNum == 7 && yearAndWeek[1] == 0) {
			c.add(Calendar.DAY_OF_MONTH, -1);
			return getYearAndWeekNumber(c);
		}
		return yearAndWeek;
	}

	/**
	 * 计算指定日期是周几
	 * 
	 * @param dt
	 * @return
	 */
	public static int getWeekDayNumber(Calendar dt) {
		for (int i = 1; i <= 7; i++) {
			if (getLastChineseWeekDay(dt, i).compareTo(dt) == 0) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 从指定日期开始，往前推算指定周几的日期, 如指定日期正好是指定的周几数，则返回该指定日期
	 * 
	 * @param lastWeekDay
	 *            ：从1至7的整数，如前一个周一为1, 前一个周二为２,　前一个周日为7
	 * @return
	 */
	public static Calendar getLastChineseWeekDay(Calendar calendar, int lastWeekDay) {
		if (lastWeekDay < 1 || lastWeekDay > 7) {
			throw new IllegalArgumentException("lastWeekDay:" + lastWeekDay + " must be larger than 0 or less than 8");
		}
		int[] chineseWeekDay = { 7, 1, 2, 3, 4, 5, 6 }; // 中国习惯的周几，如周一为１,周日为7
		if (chineseWeekDay[calendar.get(Calendar.DAY_OF_WEEK) - 1] == lastWeekDay) {
			return calendar;
		}
		for (int i = 1; i < 8; i++) {
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			if (chineseWeekDay[calendar.get(Calendar.DAY_OF_WEEK) - 1] == lastWeekDay) {
				return calendar;
			}
		}
		return null;
	}

	/**
	 * 如果全部未开赛，则方案未开赛;如果有一场比赛中，则方案比赛中;如果全部比赛完毕，则方案比赛完毕
	 */
	public static TjRaceStatus checkRaceStatus(List<Integer> raceStatus) {
		int notMatchCount = 0;
		int matchedCount = 0;
		for (Integer item : raceStatus) {
			if (item == TjRaceStatus.matching.getIndex()) {
				return TjRaceStatus.matching;
			} else if (item == TjRaceStatus.not_match.getIndex()) {
				notMatchCount++;
			} else if (item == TjRaceStatus.matched.getIndex() || item == TjRaceStatus.cancel.getIndex()) {
				matchedCount++;
			}
		}
		if (notMatchCount == raceStatus.size()) {
			return TjRaceStatus.not_match;
		} else if (matchedCount == raceStatus.size()) {
			return TjRaceStatus.matched;
		}
		return TjRaceStatus.matching;

	}

	/**
	 * 根据字符串生成数组
	 * 
	 * @param raceIds
	 * @return
	 */
	public static List<Long> getRaceList(String raceIds) {
		String[] array = raceIds.split(",");
		List<Long> list = new ArrayList<Long>();
		for (String item : array) {
			list.add(Long.valueOf(item));
		}
		return list;
	}

	/**
	 * 抓取足球赛事比分
	 * 
	 */
	public static Map<String, String> catchZcScore(List<String> matchIds) {
		Map<String, String> map = catchZcScoreLive(matchIds);
		for (String matchId : map.keySet()) {
			String score = map.get(matchId);
			if (StringUtils.isBlank(score)) {
				score = catchZcScoreResult(matchId);
				map.put(matchId, score);
			}
		}
		return map;
	}

	public static String catchZcScoreResult(String matchId) {
		String url = "http://info.sporttery.cn/football/pool_result.php?id=" + matchId;
		Document document = getDocument(url);
		if (document == null) {
			return null;
		}
		Elements elements = document.select("tr.Tr3");
		if (elements.size() == 0) {
			return null;
		}
		Element element = elements.get(1);
		Elements tdElements = element.select("td");
		if (tdElements.size() < 5) {
			return null;
		}
		return tdElements.get(4).text();
	}

	private static Document getDocument(String url) {
		try {
			return Jsoup.connect(url).timeout(6000).userAgent("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)").get();
		} catch (Exception e) {
			logger.error("使用Jsoup获取网页失败");
			return null;
		}
	}

	/**
	 * 抓取足球赛事比分
	 * 
	 */
	public static Map<String, String> catchZcScoreLive(List<String> matchIds) {
		Map<String, String> map = new HashMap<String, String>();
		for (String matchId : matchIds) {
			map.put(matchId, null);
		}
		Document document = getDocument("http://i.sporttery.cn/api/match_live/get_match_list?t=" + System.currentTimeMillis());
		if (document == null) {
			return map;
		}
		String content = document.body().text();
		if (StringUtils.isBlank(content)) {
			logger.error("从Sporttery.cn获取比分直播内容为空");
			return map;
		}
		content = content.replace("var match_list = ", "");
		content = content.substring(0, content.length() - 1);
		JSONObject topObj = JSON.parseObject(content);
		if (topObj == null) {
			logger.error("比分直播JSON内容无法解析");
			return map;
		}
		for (String matchId : matchIds) {
			JSONObject kidObj = (JSONObject) topObj.get("_" + matchId);
			if (kidObj == null) {
				continue;
			}
			String host = (String) kidObj.get("fs_h");
			String guest = (String) kidObj.get("fs_a");
			String status = (String) kidObj.get("status");
			if (!status.equals("Played")) {
				continue;
			}
			if (StringUtils.isBlank(host) || StringUtils.isBlank(guest)) {
				continue;
			}
			map.put(matchId, host + ":" + guest);
		}
		return map;
	}

	public static void main(String[] args) {
		TjRace race = new TjRace();
		Date time = DateUtil.getDate2("2015-08-01 00:00:00");
		race.setMatchNo(1);
		race.setPublishTime(time);
		race.setHandicap("0;_;1/1.5;2/2.5");
		race.setSp("1.25,0.9,1.26;_;1.25,0.9;1.25,0.9");
		RaceHandicap handicap = buildRaceHandicap(race);
		String handicapStr = "0;_;1/1.5;2/2;2;_";
		System.out.println(formatHandicap(handicapStr));
		System.out.println(getHandicapStr("-1", "胜平负单关,胜平负过关,让球胜平负单关", "三球半", "两球"));
		System.out.println(handicap.toString());
		System.out.println(handicap.getPublishTime());
		System.out.println(buildUniqueMatchNo(race));
		List<String> list = new ArrayList<String>();
		list.add("80082");
		Map<String, String> map = catchZcScore(list);
		for (String key : map.keySet()) {
			System.out.println(map.get(key));
		}
		System.out.println(catchZcScoreResult("80080"));
	}
}
