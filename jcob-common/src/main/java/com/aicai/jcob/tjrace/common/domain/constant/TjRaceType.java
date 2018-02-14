package com.aicai.jcob.tjrace.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

/**
 * 赛事类型
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月25日
 */
public class TjRaceType extends BaseType {
	private static final long serialVersionUID = 3402750231176272364L;
	public TjRaceType(Integer status, String description) {
		super(status, description);
	}
	public static TjRaceType JJ_ZC = new TjRaceType(1, "竞技足球赛事");
	public static TjRaceType JJ_LC = new TjRaceType(2, "竞技篮球赛事");
	public static List<TjRaceType> getAllList() {
		return getAll(TjRaceType.class);
	}
	public static  TjRaceType valueOf(Integer index){
		return valueOf(TjRaceType.class, index);
	}
}
