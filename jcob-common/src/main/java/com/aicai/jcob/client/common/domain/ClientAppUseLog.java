package com.aicai.jcob.client.common.domain;

import java.io.Serializable;
import java.util.Calendar;

import com.aicai.jcob.client.common.constant.AppUseType;
import com.aicai.jcob.common.constant.ClientType;
/**
 * 
 * app使用记录 （激活，启动）
 * @project jcob-common
 * @author duannp
 * @date 2016年4月12日
 */
public class ClientAppUseLog implements Serializable {
	private static final long serialVersionUID = -2392100333573950256L;
	
	public ClientAppUseLog(){
		
	}
	
	public ClientAppUseLog(String imei,Integer clientType,Integer channel,String version){
		this.imei = imei;
		this.clientType = clientType;
		this.channel = channel;
		this.version = version;
	}

	/**  */
    private Long id;

    /** 设备唯一id */
    private String imei;

    /** app使用类型：1激活，2启动 */
    private Integer appUseType = AppUseType.activate.getIndex();

    /** 客户端类型：1android,2ios */
    private Integer clientType = ClientType.android.getIndex();

    /** 渠道id */
    private Integer channel;

    /** 版本 */
    private String version;

    /** 创建时间 */
    private Calendar createTime = Calendar.getInstance();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public Integer getAppUseType() {
        return appUseType;
    }

    public void setAppUseType(Integer appUseType) {
        this.appUseType = appUseType;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }
}