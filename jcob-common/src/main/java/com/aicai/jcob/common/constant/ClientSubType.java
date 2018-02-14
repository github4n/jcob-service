package com.aicai.jcob.common.constant;

import java.util.ArrayList;
import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

/**
 * 客户端类型
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月22日
 */
public class ClientSubType extends BaseType {
	private static final long serialVersionUID = 3402750231176272364L;
	public ClientSubType(Integer status, String description) {
		super(status, description);
	}
	public static ClientSubType unKnow = new ClientSubType(0, "未知");
	public static ClientSubType android = new ClientSubType(1, "android体育版");
	public static ClientSubType ios = new ClientSubType(2, "ios体育版");
	public static ClientSubType h5 = new ClientSubType(3, "h5体育版");
	public static ClientSubType web = new ClientSubType(4, "web体育版");
	public static List<ClientSubType> getAllList() {
		return getAll(ClientSubType.class);
	}
	public static  ClientSubType valueOf(Integer index){
		return valueOf(ClientSubType.class, index);
	}
	
	 public static List<ClientSubType> getMobileSellClient(){
		 List<ClientSubType> sellClientList=new ArrayList<ClientSubType>();
		 sellClientList.add(android);
		 sellClientList.add(ios);
		 sellClientList.add(h5);
		 return sellClientList;
	 }
}
