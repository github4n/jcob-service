package com.aicai.jcob.adver.common.constant;

import com.aicai.jcob.common.constant.BaseType;

import java.util.List;

public class AdvertiseLocation  extends BaseType{


	public AdvertiseLocation(Integer index, String description) {
		super(index, description);
	}

	public static final AdvertiseLocation HOME_PAGE = new AdvertiseLocation(1, "首页焦点图");
	public static final AdvertiseLocation START_PAGE = new AdvertiseLocation(2, "启动页");

	public static List<AdvertiseLocation> getAllList() {
		return getAll(AdvertiseLocation.class);
	}

	public static AdvertiseLocation valueOf(Integer index) {
		return valueOf(AdvertiseLocation.class, index);
	}
}
