package com.aicai.jcob.tjplan.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

/**
 * 中奖状态
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月25日
 */
public class TjPlanShowStatus extends BaseType{

	private static final long serialVersionUID = 1498268996322980997L;

	protected TjPlanShowStatus(Integer index, String description) {
		super(index, description);
	}
	/**
	 * 不显示
	 */
	public final static TjPlanShowStatus not_show = new TjPlanShowStatus(0,"不显示");
	
	/**
	 * 显示
	 */
	public final static TjPlanShowStatus show = new TjPlanShowStatus(1,"显示");

	
	public static List<TjPlanShowStatus> getAllList(){
		return getAll(TjPlanShowStatus.class);
	}
	
	public static TjPlanShowStatus valueOf(Integer index){
		return valueOf(TjPlanShowStatus.class,index);
	}
	
}
