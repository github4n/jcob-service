package com.aicai.jcob.game.handler.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.game.common.domain.constant.GameType;
import com.aicai.jcob.game.handler.JjGameCheckHandler;
import com.aicai.jcob.tjplan.common.domain.TjPlanItem;
import com.aicai.jcob.tjplan.domain.TjplanCheckResult;
/**
 * 足球大小球推荐内容检查
 * 
 * @project jcob-server
 * @author duannp
 * @date 2016年2月2日
 */
@Component
public class JcZqDXQCheckHandler implements JjGameCheckHandler {


	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private JcZqCommonCheckHandler jcZqCommonCheckHandler;
	@Override
	public Integer getGameId() {
		return GameType.JJ_ZC_WW_DXQ.getIndex();
	}

	@Override
	public ModelResult<TjplanCheckResult> checkPlanItem(List<TjPlanItem> tjPlanItem) {
//		110212302[2/2.5](0-1.3)
		String regex = "^\\d{9}(\\[.*\\]){0,1}\\(\\d+\\-\\d+\\.{0,1}\\d+{0,4}\\)$";
		return jcZqCommonCheckHandler.checkeCommon(tjPlanItem, regex);
	}

	public static void main(String[] args){
		String regex = "^\\d{9}(\\[.*\\]){0,1}\\(\\d+\\-\\d+\\.{0,1}\\d+{0,4}\\)$";
		Pattern pattern = Pattern.compile(regex);
		System.out.println(pattern.matcher("110212302[2/2.5](0-1.3)").find());
	}

}
