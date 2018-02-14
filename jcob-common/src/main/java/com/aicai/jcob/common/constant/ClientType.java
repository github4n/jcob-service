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
public class ClientType extends BaseType {
	private static final long serialVersionUID = 3402750231176272364L;
	public ClientType(Integer status, String description) {
		super(status, description);
	}
	public static ClientType unKnow = new ClientType(0, "未知");
	public static ClientType android = new ClientType(1, "android");
	public static ClientType ios = new ClientType(2, "ios");
	public static ClientType h5 = new ClientType(3, "h5");
	public static ClientType web = new ClientType(4, "web");
	public static List<ClientType> getAllList() {
		return getAll(ClientType.class);
	}
	public static  ClientType valueOf(Integer index){
		return valueOf(ClientType.class, index);
	}
	
	 public static List<ClientType> getMobileSellClient(){
		 List<ClientType> sellClientList=new ArrayList<ClientType>();
		 sellClientList.add(android);
		 sellClientList.add(ios);
		 sellClientList.add(h5);
		 return sellClientList;
	 }
}
