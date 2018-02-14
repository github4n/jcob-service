package com.aicai.jcob.game.handler.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.tjplan.common.domain.TjPlanItem;
import com.aicai.jcob.tjplan.domain.TjplanCheckResult;
import com.aicai.jcob.tjrace.common.domain.TjRace;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceStatus;
import com.aicai.jcob.tjrace.manager.TjRaceManager;

/**
 * 
 * 足球验证基类，抽取公用方法
 * @project jcob-server
 * @author duannp
 * @date 2016年2月3日
 */
@Component
public  class JcZqCommonCheckHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private TjRaceManager tjRaceManager;
	
	public ModelResult<TjplanCheckResult> checkeCommon(List<TjPlanItem> tjPlanItem,String ctRegx){
		ModelResult<TjplanCheckResult> modelResult = new ModelResult<TjplanCheckResult>();
		TjplanCheckResult tjplanCheckResult = new TjplanCheckResult();
		if (tjPlanItem == null || tjPlanItem.size() <= 0) {
			return modelResult.withError("param.not.null","tjPlanItem不能为null");
		}
		Pattern pattern = Pattern.compile(ctRegx);
		Calendar minDate = null;
		Calendar maxDate = null;
		Long minRaceId = 0L;
		Long maxRaceId = 0L;
		List<Long> raceIdList = new ArrayList<Long>();
		for (TjPlanItem item : tjPlanItem) {
			String ct = item.getContent();
			if (StringUtils.isBlank(ct)) {
				return modelResult.withError("planitem.content.not.null","推荐方案内容不能为null");
			}
			if (!pattern.matcher(ct).find()) {
				logger.info("推荐方案内容[{}]格式错误",ct);
				return modelResult.withError("planitem.content.invalid.format","推荐方案内容格式错误");
			}
			//验证赛事状态
			TjRace tjRace = tjRaceManager.queryRaceById(item.getRaceId());
			if (tjRace == null || tjRace.getStatus() != TjRaceStatus.not_match.getIndex()
					|| tjRace.getMatchTime().before(new Date())) {
				logger.info("赛事[{}]当前状态不允许发布推荐",item.getRaceId());
				return modelResult.withError("planitem.tjrace.invalid.status","赛事不允许发布推荐!");
			}
			if (minDate == null && maxDate == null) {
				minDate = Calendar.getInstance();
				minDate.setTime(tjRace.getMatchTime());
				maxDate = Calendar.getInstance();
				maxDate.setTime(tjRace.getMatchTime());
				minRaceId = tjRace.getId();
				maxRaceId = tjRace.getId();
			}
			if (tjRace.getMatchTime().after(maxDate.getTime())) {
				maxDate.setTime(tjRace.getMatchTime());
				maxRaceId = tjRace.getId();
			}
			if (tjRace.getMatchTime().before(minDate.getTime())) {
				minDate.setTime(tjRace.getMatchTime());
				minRaceId = tjRace.getId();
			}
			raceIdList.add(tjRace.getId());
		}
		tjplanCheckResult.setMaxMatchTime(maxDate);
		tjplanCheckResult.setMaxRaceId(maxRaceId);
		tjplanCheckResult.setMinMatchTime(minDate);
		tjplanCheckResult.setMinRaceId(minRaceId);
		tjplanCheckResult.setRaceIdList(raceIdList);
		tjplanCheckResult.setTjPlanItemList(tjPlanItem);
		modelResult.setModel(tjplanCheckResult);
		return modelResult;
	}
}
