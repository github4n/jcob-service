package com.aicai.jcob.common.constant;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
/**
 * 
 * 替代枚举类型
 * @project jcob-common
 * @author duannp
 * @date 2016年1月22日
 */
public abstract class BaseType implements Serializable {

	private static final long serialVersionUID = -199712849987884225L;

	private Integer index; // 顺序
	private String description;// 描述
	private String outDescription;// 增加外部描述

	protected BaseType(Integer index, String description) {
		this.index = index;
		this.description = description;
	}
	protected BaseType(Integer index, String description,String outDescription) {
		this.index = index;
		this.description = description;
		this.outDescription = outDescription;
	}

	@SuppressWarnings("unchecked")
	public static <T extends BaseType> List<T> getAll(Class<T> clazz) {
		List<T> list = new ArrayList<T>();
		try {
			Field[] fieldlist = clazz.getDeclaredFields();
			for (Field field : fieldlist) {
				if (field.getType().isAssignableFrom(clazz)) {
					list.add((T) field.get(null));
				}
			}
		} catch (Exception e) {
		}
		return list;
	}

	public static <T extends BaseType> T valueOf(Class<T> clazz,
			Integer index) {
		try {
			List<T> list = (List<T>) getAll(clazz);
			for (T t : list) {
				if (t.getIndex() == index) {
					return t;
				}
			}
		} catch (Exception e) {
		}
		return null;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		BaseType baseType = valueOf(this.getClass(), index);
		if (null != baseType) {

			this.index = baseType.getIndex();
			this.description = baseType.getDescription();
		}
	}

	/**
	 * 此方法不能删除,使类型能直接比较
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseType other = (BaseType) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		return true;
	}

	public String getDescription() {
		return description;
	}

	public String toString() {
		return JSON.toJSONString(this);
	}
	public String getOutDescription() {
		return outDescription;
	}
	
	
}
