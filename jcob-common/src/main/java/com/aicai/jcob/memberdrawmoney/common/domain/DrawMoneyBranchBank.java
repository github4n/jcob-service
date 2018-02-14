package com.aicai.jcob.memberdrawmoney.common.domain;

import java.io.Serializable;

/**
 * 提款银行分支行
 * @author alanpeng
 *
 */
public class DrawMoneyBranchBank  implements Serializable {

	private static final long serialVersionUID = -2598183603226205068L;
	private int id;
	private int areaId;// 省市对应表ID
	private int parentId;// 银行ID(银行信息表ID)
	private String name;// 分支行名称

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
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

}
