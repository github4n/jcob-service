package com.aicai.jcob.tjexpert.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

/**
 * 专家等级操作类型
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月22日
 */
public class TjExepertLevelHandleType extends BaseType {
	private static final long serialVersionUID = 3402750231176272364L;
	public TjExepertLevelHandleType(Integer status, String description) {
		super(status, description);
	}
	public static final TjExepertLevelHandleType up = new TjExepertLevelHandleType(1, "升级");
	public static final TjExepertLevelHandleType down = new TjExepertLevelHandleType(2, "降级");
	public static List<TjExepertLevelHandleType> getAllList() {
		return getAll(TjExepertLevelHandleType.class);
	}
	public static  TjExepertLevelHandleType valueOf(Integer index){
		return valueOf(TjExepertLevelHandleType.class, index);
	}
}
