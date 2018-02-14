package com.aicai.jcob.tjexpert.common.domain.constant;

import java.util.ArrayList;
import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

/**
 * 专家等级
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月22日
 */
public class TjExpertLevel extends BaseType {
	private static final long serialVersionUID = 3402750231176272364L;
	public TjExpertLevel(Integer status, String description) {
		super(status, description);
	}
	 /** 等级：0普通用户，1实验室专家，2正式专家,3带v的正式专家 */
	public static final TjExpertLevel normal = new TjExpertLevel(0, "普通用户");
	public static final TjExpertLevel test_expert = new TjExpertLevel(1, "实验室专家");
	public static final TjExpertLevel formal_expert = new TjExpertLevel(2, "正式专家");
	public static final TjExpertLevel formal_expert_v = new TjExpertLevel(3, "正式专家带v");
	public static List<TjExpertLevel> getAllList() {
		return getAll(TjExpertLevel.class);
	}
	
	public static List<TjExpertLevel> getExpertList() {
		List<TjExpertLevel> list = new ArrayList<TjExpertLevel>();
		list.add(TjExpertLevel.test_expert);
		list.add(TjExpertLevel.formal_expert);
		list.add(TjExpertLevel.formal_expert_v);
		return list;
	}
	
	public static  TjExpertLevel valueOf(Integer index){
		return valueOf(TjExpertLevel.class, index);
	}
}
