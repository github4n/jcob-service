package com.aicai.jcob.datachange;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.aicai.datachange.client.manager.DataChangeManager;
import com.aicai.datachange.common.constant.ChangeTypeConstant;
import com.aicai.datachange.common.domain.DataChangeDO;

@Component
public class DataChangeFactory {

	@Autowired
	@Qualifier("jcobDataChangeManager")
	private DataChangeManager dataChangeManager;
	
	/**
	 * datachange消息发送
	 * @param dataId
	 *  数据主键id
	 * @param dataType
	 * com.aicai.datachange.common.constant.DataType
	 * @param changeType
	 * com.aicai.datachange.common.constant.ChangeTypeConstant
	 */
	public void sendDataChangeForInsert(Long dataId,String dataType){
		sendDataChange(dataId, dataType, ChangeTypeConstant.DATA_INSERT, null, null,null,null,null,null);
	}
	/**
	 * 
	  * datachange消息发送
	 * @param dataId
	 *  数据主键id
	 * @param dataType
	 * com.aicai.datachange.common.constant.DataType
	 * @param newStatus1
	 * 修改后状态1 可null
	 * @param newStatus2
	 * @param oldStatus1
	 * 修改前状态1 可null
	 * @param oldStatus2
	 */
	public void sendDataChangeForModify(Long dataId,String dataType,Integer newStatus1, Integer newStatus2, Integer oldStatus1, Integer oldStatus2){
		sendDataChange(dataId, dataType, ChangeTypeConstant.DATA_MODIFY, null, null, newStatus1,  newStatus2,  oldStatus1,  oldStatus2);
	}
	/**
	 * datachange消息发送
	 * @param dataId
	 *  数据主键id
	 * @param dataType
	 * com.aicai.datachange.common.constant.DataType
	 * @param changeType
	 * com.aicai.datachange.common.constant.ChangeTypeConstant
	 * @param changeFiedBitList
	 * 当多个字段修改，接受方要明确知道哪个字段修改传递次参数@see com.aicai.datachange.common.constant.ChangeFieldBitConstant
	 * @param transferParamMap
	 * 当有需要附加信息传递时以键值对形式传递，接收方通过调用dataChangeDO.getOpaqueData(key)获得值
     * @param newStatus1
	 * 修改后状态1 可null
	 * @param newStatus2
	 * @param oldStatus1
	 * 修改前状态1 可null
	 * @param oldStatus2
	 */
	public void sendDataChange(Long dataId,String dataType,String changeType,List<Long> changeFiedBitList,Map<String, Object> transferParamMap
			,Integer newStatus1, Integer newStatus2, Integer oldStatus1, Integer oldStatus2){
		DataChangeDO dataChangeDO = new DataChangeDO();
		
		dataChangeDO.setDataId(dataId);
		dataChangeDO.setDataType(dataType);
		dataChangeDO.setChangeType(changeType);
		if (newStatus1 != null) {
			dataChangeDO.setNewStatus1(newStatus1);
		}
		if (newStatus2 != null) {
			dataChangeDO.setNewStatus2(newStatus2);
		}
		if (oldStatus1 != null) {
			dataChangeDO.setOldStatus1(oldStatus1);
		}
		if (oldStatus2 != null) {
			dataChangeDO.setOldStatus2(oldStatus2);
		}
		if (changeFiedBitList != null && changeFiedBitList.size() > 0) {
			for (Long changeFiedBit : changeFiedBitList) {
				dataChangeDO.addChangeFieldBit(changeFiedBit);
			}
		}
		if (transferParamMap != null && transferParamMap.size() > 0) {
			for (Entry<String, Object> entryMap : transferParamMap.entrySet()) {
				dataChangeDO.putOpaqueData(entryMap.getKey(),entryMap.getValue());
			}
		}
        dataChangeManager.sendMessage(dataChangeDO);
	}
}
