package com.aicai.jcob.client.common.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

/**
 * app使用类型
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年4月12日
 */
public class AppUseType extends BaseType {
	private static final long serialVersionUID = 3402750231176272364L;
	public AppUseType(Integer status, String description) {
		super(status, description);
	}
	public static AppUseType activate = new AppUseType(1, "激活");
	public static AppUseType startup = new AppUseType(2, "启动");
	
	public static List<AppUseType> getAllList() {
		return getAll(AppUseType.class);
	}
	public static  AppUseType valueOf(Integer index){
		return valueOf(AppUseType.class, index);
	}
}
