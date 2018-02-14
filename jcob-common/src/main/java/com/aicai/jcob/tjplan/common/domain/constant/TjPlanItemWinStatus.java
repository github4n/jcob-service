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
public class TjPlanItemWinStatus extends BaseType{

	private static final long serialVersionUID = 1498268996322980997L;

	protected TjPlanItemWinStatus(Integer index, String description) {
		super(index, description);
	}
	/**
	 * 未开
	 */
	public final static TjPlanItemWinStatus not_open = new TjPlanItemWinStatus(0,"未开");
	
	/**
	 * 中奖状态：1未中
	 */
	public final static TjPlanItemWinStatus not_award = new TjPlanItemWinStatus(1,"未中");
	/**
	 * 中奖状态：2已中
	 */
	public final static TjPlanItemWinStatus award = new TjPlanItemWinStatus(2,"已中");
	/**
	 * 取消
	 */
	public final static TjPlanItemWinStatus cancel= new TjPlanItemWinStatus(3,"取消");
	
	public static List<TjPlanItemWinStatus> getAllList(){
		return getAll(TjPlanItemWinStatus.class);
	}
	
	public static TjPlanItemWinStatus valueOf(Integer index){
		return valueOf(TjPlanItemWinStatus.class,index);
	}
	
}
