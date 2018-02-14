package com.aicai.jcob.memberdrawmoney.common.domain;

import java.io.Serializable;

/**
 * 省市对应表
 * 
 * @author alanpeng
 * 
 */
public class DrawMoneyProvinceCity implements Serializable {

	private static final long serialVersionUID = -2598183603226205068L;
	private int id;
	private int parentId;// 父级ID（省ID），0时为省
	private String name;// 省名或市名
	/**省市代码**/
	private String code;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
