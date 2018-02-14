package com.aicai.jcob.adver.manager.impl;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericDao;
import com.aicai.jcob.adver.common.constant.BusinessType;
import com.aicai.jcob.adver.common.constant.ConditionType;
import com.aicai.jcob.adver.common.constant.UsingStatus;
import com.aicai.jcob.adver.common.domain.ClientFunctionRecord;
import com.aicai.jcob.adver.common.domain.ClientVersionAgentId;
import com.aicai.jcob.adver.common.domain.option.ClientFunctionRecordOption;
import com.aicai.jcob.adver.common.domain.option.ClientVersionAgentIdOption;
import com.aicai.jcob.adver.common.domain.option.ClientVersionAgentIdVo;
import com.aicai.jcob.adver.manager.ClientFunctionRecordManager;
import com.aicai.jcob.adver.manager.ClientVersionAgentIdManager;
import com.aicai.jcob.common.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by tianlun.wu on 2016/2/25 0025.
 */
public class ClientFunctionRecordManagerImpl implements ClientFunctionRecordManager {
    private static final Logger logger = LoggerFactory.getLogger(ClientFunctionRecordManagerImpl.class);

    @Resource(name = "tjplanDbDao")
    private GenericDao dao;

    @Resource
    private ClientVersionAgentIdManager clientVersionAgentIdManager;

    @Override
    public ModelResult<Boolean> insert(ClientFunctionRecord clientFunctionRecord, ClientVersionAgentIdVo clientVersionAgentId) {
        try {

            Calendar nowTime = Calendar.getInstance();
            nowTime.add(Calendar.MONTH, -1);
            if(clientFunctionRecord.getStartDate() == null ){
                clientFunctionRecord.setStartDate(nowTime);
            }
            nowTime = Calendar.getInstance();
            nowTime.add(Calendar.YEAR, 5);
            if(clientFunctionRecord.getEndDate() == null){
                clientFunctionRecord.setEndDate(nowTime);
            }
            clientFunctionRecord.setCreateDate(Calendar.getInstance());

/*            if(clientFunctionRecord.getRecordState() == UsingStatus.using.getIndex()){
                Map<String, Object> param = new HashMap<>();
                param.put("clientType", clientFunctionRecord.getClientType());
                param.put("topNumber", clientFunctionRecord.getTopNumber());
                param.put("recordState", clientFunctionRecord.getRecordState());
                param.put("adLocation", clientFunctionRecord.getAdLocation());
                List<ClientFunctionRecord> list = queryIsExist(param);
                if(list !=null && list.size() >0){
                    logger.info("clientType:{} title:{}:已经存在了 位置:{} 顺序:{} 状态 :{} 广告图:",  clientFunctionRecord.getClientType(),
                            clientFunctionRecord.getTitle(), clientFunctionRecord.getAdLocation(), clientFunctionRecord.getTopNumber(),
                            clientFunctionRecord.getRecordState());
                    return new ModelResult<>().withError("exception", "已经存在了同样位置的,同样顺序,同样状态的广告图");
                }
            }*/

            int result = dao.insertAndReturnAffectedCount("com.mybatis.mapper.ClientFunctionRecordMapper.insert_client_record",
                    clientFunctionRecord);
            if (result == 1) {
                clientVersionAgentId.setBusinessType(BusinessType.adverLocation.getIndex());
                clientVersionAgentId.setBusinessId(clientFunctionRecord.getId());
                clientVersionAgentIdManager.insertMulti(clientVersionAgentId);
                return new ModelResult<>(Boolean.TRUE);
            }
            return new ModelResult<>().withError("exception", "数据插入失败");
        } catch (Exception e) {
            logger.error("com.mybatis.mapper.ClientFunctionRecordMapper.insert_client_record 报错:{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public ModelResult<Boolean> update(ClientFunctionRecord clientFunctionRecord) {
        try {
            Calendar nowTime = Calendar.getInstance();
            nowTime.add(Calendar.MONTH, -1);
            if(clientFunctionRecord.getStartDate() == null ){
                clientFunctionRecord.setStartDate(nowTime);
            }
            nowTime = Calendar.getInstance();
            nowTime.add(Calendar.YEAR, 5);
            if(clientFunctionRecord.getEndDate() == null){
                clientFunctionRecord.setEndDate(nowTime);
            }

            Map<String, Object> param = JSONUtils.object2MapSpecail(clientFunctionRecord);
            int result = dao.update("com.mybatis.mapper.ClientFunctionRecordMapper.update_cfrecord_by_id", param);
            if (result == 1) {
                return new ModelResult<>(Boolean.TRUE);
            }
            return new ModelResult<>().withError("exception", "更新失败");
        } catch (Exception e) {
            logger.error("com.mybatis.mapper.ClientFunctionRecordMapper.update_cfrecord_by_id 报错:{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public ModelResult<Boolean> deleteByIds(List<Integer> ids) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("listArray", ids);
            int result = dao.update("com.mybatis.mapper.ClientFunctionRecordMapper.delete_client_record", map);
            if (result == 0) {
                return new ModelResult<>().withError("exception", "删除失败");
            }
            return new ModelResult<>(Boolean.TRUE);
        } catch (Exception e) {
            logger.error("com.mybatis.mapper.ClientFunctionRecordMapper.delete_client_record 报错:{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public ModelResult<ClientFunctionRecord> queryById(Integer id) {
        try {
            ClientFunctionRecord clientFunctionRecord = query(id);
            return new ModelResult<>(clientFunctionRecord);
        } catch (Exception e) {
            logger.error("com.mybatis.mapper.ClientFunctionRecordMapper.query_cfrecord_by_id 报错:{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public ModelResult<DataPage<ClientFunctionRecord>> queryByPage(ClientFunctionRecordOption option, DataPage<ClientFunctionRecord> dataPage) {
        try {
            ClientVersionAgentIdOption clientVersionAgentIdOption = new ClientVersionAgentIdOption();
            clientVersionAgentIdOption.setAgentId(option.getAgentId());
            clientVersionAgentIdOption.setClientVersion(option.getVersion());
            clientVersionAgentIdOption.setClientType(option.getClientType());
            clientVersionAgentIdOption.setBusinessType(BusinessType.adverLocation.getIndex());
            List<ClientVersionAgentId> list = clientVersionAgentIdManager.queryListGroupByBusinessId(clientVersionAgentIdOption);

            if(list == null || list.size() ==0 ){
                logger.info("根据条件:{}", option.toString());
                return new ModelResult<>().withError("exception", "根据条件查询不到数据");
            }
            List<Integer> ids = new ArrayList<>();
            for (ClientVersionAgentId item : list) {
                ids.add(item.getBusinessId());
            }

            DataPage<ClientFunctionRecord> result = new DataPage<>(dataPage.getPageNo(), dataPage.getPageSize());
            Map<String, Object> map = JSONUtils.object2MapSpecail(option);
            map.put("ids", ids);
            map.put("startIndex", (dataPage.getPageNo() - 1) * dataPage.getPageSize());
            map.put("pageSize", dataPage.getPageSize());
            return new ModelResult<>(dao.queryPage("com.mybatis.mapper.ClientFunctionRecordMapper.query_client_record_count",
                    "com.mybatis.mapper.ClientFunctionRecordMapper.query_client_function_list", map, result));
        } catch (Exception e) {
            logger.error("com.mybatis.mapper.ClientFunctionRecordMapper.query_client_record_count 异常:{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public ModelResult<Boolean> updateStatus(Integer id) {
        try {
            ClientFunctionRecord clientFunctionRecord = query(id);
            Map<String, Object> param = new HashMap<>();
            param.put("id", id);
            if (clientFunctionRecord.getRecordState() == UsingStatus.using.getIndex()) {
                param.put("recordState", UsingStatus.stopping.getIndex());
            } else {
                param.put("recordState", UsingStatus.using.getIndex());
            }
            int result = dao.update("com.mybatis.mapper.ClientFunctionRecordMapper.update_cfrecord_by_id", param);
            if (result == 1) {
                return new ModelResult<>(Boolean.TRUE);
            }
            return new ModelResult<>().withError("exception", "更新失败");
        } catch (Exception e) {
            logger.error("com.mybatis.mapper.ClientFunctionRecordMapper.update_cfrecord_by_id 异常:{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public ModelResult<Boolean> deleteById(Integer id) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            ClientFunctionRecord record = query(id);
            if (record.getRecordState() == UsingStatus.using.getIndex()) {
                return new ModelResult<>().withError("exception", "记录状态为使用中");
            }
            int result = dao.update("com.mybatis.mapper.ClientFunctionRecordMapper.delete_client_record_id", map);
            if (result == 0) {
                return new ModelResult<>().withError("exception", "删除失败");
            }

            clientVersionAgentIdManager.deleteByBusinessId(id);

            return new ModelResult<>(Boolean.TRUE);
        } catch (Exception e) {
            logger.error("com.mybatis.mapper.ClientFunctionRecordMapper.delete_client_record 报错:{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public ModelResult<List<ClientFunctionRecord>> queryList(Integer clientType, String version, Integer agentId, Integer adLocation) {
        logger.info("ClientFunctionRecordManagerImpl.queryList() clientType:{},version:{},agentId:{},position:{}", clientType, version, agentId, adLocation);
        //先查询满足 adLocation和clientType 的广告图

        try {

            Map<String, Object> param = new HashMap<>();
            param.put("adLocation", adLocation);
            param.put("clientType", clientType);
            param.put("nowTime", Calendar.getInstance());
            List<ClientFunctionRecord> list = dao.queryList("com.mybatis.mapper.ClientFunctionRecordMapper.queryListClient", param) ;
            List<ClientFunctionRecord> resultList = new ArrayList<>();
            if(list !=null && !list.isEmpty()){

                for(int i=0; i<list.size(); i++){
                    ClientFunctionRecord clientFunctionRecord = list.get(i);
                    if(clientFunctionRecord.getTipsStatusType() == ConditionType.ALL.getIndex()){
                        resultList.add(clientFunctionRecord);
                        continue;
                    }
                    ClientVersionAgentIdOption clientVersionAgentIdOption = new ClientVersionAgentIdOption(
                            BusinessType.adverLocation.getIndex(), clientType, version, clientFunctionRecord.getId(), agentId);
                    List<ClientVersionAgentId> clientVersionAgentIdList = clientVersionAgentIdManager.queryListByCondition(
                            clientVersionAgentIdOption);

                    if(clientFunctionRecord.getTipsStatusType() == ConditionType.zhengxuan.getIndex() &&
                            clientVersionAgentIdList !=null && clientVersionAgentIdList.size()>0){
                        resultList.add(clientFunctionRecord);
                    } else if(clientFunctionRecord.getTipsStatusType() == ConditionType.fanxuan.getIndex() &&
                            (clientVersionAgentIdList == null || clientVersionAgentIdList.size()==0)) {
                        resultList.add(clientFunctionRecord);
                    }
                }
            }

            return new ModelResult<>(resultList);
        } catch (Exception e) {
            logger.error("com.mybatis.mapper.ClientFunctionRecordMapper.queryListClient 报错:{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    private ClientFunctionRecord query(Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return dao.queryUnique("com.mybatis.mapper.ClientFunctionRecordMapper.query_cfrecord_by_id", map);
    }

    private List<ClientFunctionRecord> queryIsExist(Map param){

        return dao.queryList("com.mybatis.mapper.ClientFunctionRecordMapper.query_cfrecord_by_id", param);
    }

}
