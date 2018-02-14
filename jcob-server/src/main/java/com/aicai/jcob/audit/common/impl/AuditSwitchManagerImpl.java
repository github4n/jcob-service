package com.aicai.jcob.audit.common.impl;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericDao;
import com.aicai.jcob.adver.common.constant.BusinessType;
import com.aicai.jcob.adver.common.constant.UsingStatus;
import com.aicai.jcob.adver.common.domain.ClientFunctionRecord;
import com.aicai.jcob.adver.common.domain.ClientVersionAgentId;
import com.aicai.jcob.adver.common.domain.option.ClientVersionAgentIdOption;
import com.aicai.jcob.adver.manager.ClientVersionAgentIdManager;
import com.aicai.jcob.audit.common.AuditSwitchManager;
import com.aicai.jcob.audit.common.domain.AuditSwitch;
import com.aicai.jcob.audit.common.domain.option.AuditSwitchOption;
import com.aicai.jcob.audit.common.domain.option.AuditSwitchVo;
import com.aicai.jcob.common.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by tianlun.wu on 2016/2/28 0028.
 */
public class AuditSwitchManagerImpl implements AuditSwitchManager {

    private static final Logger logger = LoggerFactory.getLogger(AuditSwitchManagerImpl.class);

    @Resource(name = "tjplanDbDao")
    private GenericDao dao;

    @Resource
    private ClientVersionAgentIdManager clientVersionAgentIdManager ;

    @Override
    public ModelResult<Boolean> insertMulti(AuditSwitchVo auditSwitchVo) {
        List<Integer> agentIds =  auditSwitchVo.getAgentIdList();
        try {
            AuditSwitch auditSwitch = new AuditSwitch();
            auditSwitch.setClientType(auditSwitchVo.getClientType());
            auditSwitch.setOperator(auditSwitchVo.getOperator());
            auditSwitch.setStatus(auditSwitchVo.getStatus());
            auditSwitch.setType(auditSwitchVo.getType());
            insert(auditSwitch);

            ClientVersionAgentId clientVersionAgentId = null ;
            for (Integer agentId :agentIds){
                clientVersionAgentId = new ClientVersionAgentId();
                clientVersionAgentId.setAgentId(agentId);
                clientVersionAgentId.setBusinessId(auditSwitch.getId());
                clientVersionAgentId.setBusinessType(BusinessType.audit.getIndex());
                clientVersionAgentId.setClientVersion(auditSwitchVo.getVersion());
                clientVersionAgentId.setClientType(auditSwitchVo.getClientType());
                clientVersionAgentIdManager.insert(clientVersionAgentId);
            }

            return new ModelResult<>(Boolean.TRUE);
        }catch (Exception e){
            logger.error("com.mybatis.mapper.AuditSwitchMapper.insert {}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public ModelResult<Boolean> insert(AuditSwitch auditSwitch) {
        try {
            auditSwitch.setCreateTime(Calendar.getInstance());
            auditSwitch.setUpdateTime(Calendar.getInstance());
            int result = dao.insertAndReturnAffectedCount("com.mybatis.mapper.AuditSwitchMapper.insert", auditSwitch);
            if (result == 1) {
                return new ModelResult<>(Boolean.TRUE);
            }
            return new ModelResult<>().withError("exception", "数据插入失败");
        } catch (Exception e) {
            logger.error("com.mybatis.mapper.AuditSwitchMapper.insert 报错:{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public ModelResult<Boolean> update(AuditSwitch auditSwitch) {
        try {
            Map<String, Object> param = JSONUtils.object2MapSpecail(auditSwitch);
            int result = dao.update("com.mybatis.mapper.AuditSwitchMapper.update", param);
            if (result == 1) {
                return new ModelResult<>(Boolean.TRUE);
            }
            return new ModelResult<>().withError("exception", "更新失败");
        } catch (Exception e) {
            logger.error("com.mybatis.mapper.AuditSwitchMapper.update", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public ModelResult<DataPage<AuditSwitch>> queryByPage(AuditSwitchOption option, DataPage<AuditSwitch> dataPage) {
        try {

            ClientVersionAgentIdOption clientVersionAgentIdOption = new ClientVersionAgentIdOption();
            clientVersionAgentIdOption.setAgentId(option.getAgentId());
            clientVersionAgentIdOption.setClientVersion(option.getVersion());
            clientVersionAgentIdOption.setClientType(option.getClientType());
            clientVersionAgentIdOption.setBusinessType(BusinessType.audit.getIndex());
            List<ClientVersionAgentId> list = clientVersionAgentIdManager.queryListGroupByBusinessId(clientVersionAgentIdOption);

            if(list==null || list.size() ==0){
                dataPage.setTotalCount(0L);
                dataPage.setDataList(new ArrayList<>());
                logger.info("AuditSwitchManagerImpl.queryByPage 根据条件查询不到数据");
                return new ModelResult<>(dataPage);
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
            return new ModelResult<>(dao.queryPage("com.mybatis.mapper.AuditSwitchMapper.countByPage",
                    "com.mybatis.mapper.AuditSwitchMapper.selectByPage", map, dataPage));
        } catch (Exception e) {
            logger.error("com.mybatis.mapper.AuditSwitchMapper.countByPage, selectByPage 异常:{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public ModelResult<Boolean> deleteById(Integer id) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            AuditSwitch auditSwitch = query(id);
            if (auditSwitch.getStatus() == UsingStatus.using.getIndex()) {
                return new ModelResult<>().withError("exception", "记录状态为使用中");
            }
            int result = dao.update("com.mybatis.mapper.AuditSwitchMapper.deleteById", map);
            if (result == 0) {
                return new ModelResult<>().withError("exception", "删除失败");
            }
            return new ModelResult<>(Boolean.TRUE);
        } catch (Exception e) {
            logger.error("com.mybatis.mapper.AuditSwitchMapper.deleteById 报错:{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public ModelResult<Boolean> updateStatusById(Integer id, String account) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            AuditSwitch auditSwitch = query(id);
            if(auditSwitch.getStatus() == UsingStatus.using.getIndex()){
                auditSwitch.setStatus(UsingStatus.stopping.getIndex());
            } else {
                auditSwitch.setStatus(UsingStatus.using.getIndex());
            }

            auditSwitch.setOperator(account);
            auditSwitch.setUpdateTime(Calendar.getInstance());

            int result = dao.update("com.mybatis.mapper.AuditSwitchMapper.updateStatus", JSONUtils.object2MapSpecail(auditSwitch));
            if(result ==0){
                return new ModelResult<>().withError("exception","更新失败");
            }
            return new ModelResult<>(Boolean.TRUE);
        }catch (Exception e){
            logger.error("com.mybatis.mapper.AuditSwitchMapper.updateStatus 报错:{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public ModelResult<AuditSwitch> queryById(Integer id) {
        return new ModelResult<>(query(id));
    }


    @Override
    public ModelResult<Boolean> judge(Integer clientType, String version, Integer agentId, Integer functionId) {
        logger.info("AuditSwitchManagerImpl.judge() clientType:{},version:{},agentId:{},functionId:{}", clientType, version, agentId, functionId);
        ClientVersionAgentIdOption clientOption = new ClientVersionAgentIdOption(BusinessType.audit.getIndex(), clientType, version, null, agentId);
        List<ClientVersionAgentId> clientVersionAgentIdList = clientVersionAgentIdManager.queryListByCondition(clientOption);

        if(clientVersionAgentIdList == null || clientVersionAgentIdList.isEmpty()){
            logger.info("AuditSwitchManagerImpl.judge()前端根据条件：clientType:{},version:{},agentId:{}, 状态:{} 查找不到数据", clientType, version, agentId);
            return new ModelResult<>().withError("exception", "查询不到数据");
        }

        List<Integer> ids = new ArrayList<>();
        for (ClientVersionAgentId item: clientVersionAgentIdList){
            ids.add(item.getBusinessId());
        }

        AuditSwitchOption auditSwitchOption = new AuditSwitchOption();
        auditSwitchOption.setType(functionId);
        auditSwitchOption.setStatus(UsingStatus.using.getIndex());
        auditSwitchOption.setIds(ids);

        List<AuditSwitch> auditSwitcheList = queryList(auditSwitchOption);

        if(auditSwitcheList == null || auditSwitcheList.size() ==0){
            logger.info("AuditSwitchManagerImpl.judge()前端根据条件：functionId:{},使用状态:{}, 查找不到数据", functionId, UsingStatus.using.getIndex());
            return new ModelResult<>(Boolean.FALSE);

        }
        return new ModelResult<>(Boolean.TRUE);
    }

    @Override
    public List<AuditSwitch> queryList(AuditSwitchOption option) {
        Map<String, Object> param = JSONUtils.object2MapSpecail(option);

        return dao.queryList("com.mybatis.mapper.AuditSwitchMapper.selectList", param);
    }

    private AuditSwitch query(Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return dao.queryUnique("com.mybatis.mapper.AuditSwitchMapper.selectById", map);
    }
}
