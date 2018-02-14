package com.aicai.jcob.game.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JjGameHandlerFactory {

	private Map<Integer, JjGameCheckHandler> checkHandleMap = new HashMap<Integer, JjGameCheckHandler>();
	@Autowired
	public void setTaskHandleList(List<JjGameCheckHandler> checkHandleList) {
		if (checkHandleList != null && !checkHandleList.isEmpty()) {
			for (JjGameCheckHandler checkHandle : checkHandleList) {
				checkHandleMap.put(checkHandle.getGameId(), checkHandle);
			}
		}
	}
	public JjGameCheckHandler getTaskHandle(Integer gameId){
		return checkHandleMap.get(gameId);
	}
	
}
