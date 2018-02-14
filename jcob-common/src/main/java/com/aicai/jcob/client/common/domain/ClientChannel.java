package com.aicai.jcob.client.common.domain;

import java.io.Serializable;
import java.util.Date;

public class ClientChannel implements Serializable {

	private static final long serialVersionUID = 4323152584508071117L;

	private Integer id;

	private Integer parentNo;

	private String parentName;

	private Integer channelNo;

	private String channelName;

	private Integer clientType;

	private String media;

	private String url;

	private String remarks;

	private Integer isDel = 0;

	private Date updateTime;

	private Date createTime;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentNo() {
		return parentNo;
	}

	public void setParentNo(Integer parentNo) {
		this.parentNo = parentNo;
	}

	public Integer getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(Integer channelNo) {
		this.channelNo = channelNo;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	@Override
	public String toString() {
		return "ClientChannel [id=" + id + ", parentNo=" + parentNo + ", parentName=" + parentName + ", channelNo=" + channelNo
				+ ", channelName=" + channelName + ", clientType=" + clientType + ", media=" + media + ", url=" + url
				+ ", remarks=" + remarks + ", isDel=" + isDel + ", updateTime=" + updateTime + ", createTime=" + createTime + "]";
	}

}
