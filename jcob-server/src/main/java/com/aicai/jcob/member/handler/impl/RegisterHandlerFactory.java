package com.aicai.jcob.member.handler.impl;

import java.util.HashMap;
import java.util.Map;

import com.aicai.jcob.member.handler.RegisterHandler;

public class RegisterHandlerFactory {
	
	private static Map<Integer,RegisterHandler> registerHandlerMap = new HashMap<>(); 
	
	static{
		registerHandler2Factory(new MobileRegisterHandlerImpl());
		registerHandler2Factory(new EmailRegisterHandlerImpl());
		registerHandler2Factory(new AicaiThirdRegisterHandlerImpl());
		///////
		
	}
	
	private static void registerHandler2Factory(RegisterHandler registerHandler){
		
		registerHandlerMap.put(registerHandler.getRegisterType(),registerHandler);
	}
	
	public static RegisterHandler getRegisterHandler(int index){
		
		return registerHandlerMap.get(index);
	}
}
