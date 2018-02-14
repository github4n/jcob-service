package com.aicai.jcob.adver.manager.impl;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericDao;
import com.aicai.jcob.adver.common.constant.BusinessType;
import com.aicai.jcob.adver.common.domain.ClientVersionAgentId;
import com.aicai.jcob.adver.common.domain.option.ClientVersionAgentIdOption;
import com.aicai.jcob.adver.common.domain.option.ClientVersionAgentIdVo;
import com.aicai.jcob.adver.manager.ClientVersionAgentIdManager;
import com.aicai.jcob.common.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientVersionAgentIdManagerImpl implements ClientVersionAgentIdManager {

    private static final Logger logger = LoggerFactory.getLogger(ClientVersionAgentIdManagerImpl.class);

    @Resource(name = "tjplanDbDao")
    private GenericDao dao;


    @Override
    public int insert(ClientVersionAgentId clientVersionAgentId) {
        if (clientVersionAgentId.getBusinessType() == null) {
            clientVersionAgentId.setBusinessType(BusinessType.template.getIndex());
        }
        return dao.insertAndReturnAffectedCount("com.mybatis.mapper.ClientVersionAgentIdMapper.insert", clientVersionAgentId);
    }

    @Override
    public ModelResult<Boolean> insertMulti(ClientVersionAgentIdVo vo) {
        if (vo.getBusinessType() == null) {
            vo.setBusinessType(BusinessType.template.getIndex());
        }

        try {
            ClientVersionAgentId addItem = null;
            for (Integer item : vo.getAgentIds()) {
                addItem = new ClientVersionAgentId();
                addItem.setClientType(vo.getClientType());
                addItem.setBusinessId(vo.getBusinessId());
                addItem.setBusinessType(vo.getBusinessType());
                addItem.setClientVersion(vo.getClientVersion());
                addItem.setIsAnd(vo.getIsAnd());
                addItem.setAgentId(item);
                insert(addItem);
            }
        } catch (Exception e) {
            logger.error("com.mybatis.mapper.ClientVersionAgentIdMapper.insert 报错:{}, ", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
        return new ModelResult<>(Boolean.TRUE);
    }

    @Override
    public ClientVersionAgentId queryById(long id) {
        return dao.queryUnique("com.mybatis.mapper.ClientVersionAgentIdMapper.selectById", id);
    }

    @Override
    public ModelResult<Boolean> deleteById(long id) {
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("id", id);
            int result = dao.update("com.mybatis.mapper.ClientVersionAgentIdMapper.deleteById", param);
            if (result == 1) {
                return new ModelResult<>(Boolean.TRUE);
            }
            return new ModelResult<>().withError("exception", "删除失败");
        } catch (Exception e) {
            logger.error("com.mybatis.mapper.ClientVersionAgentIdMapper.deleteById 报错：{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public DataPage<ClientVersionAgentId> queryByPage(ClientVersionAgentIdOption option, DataPage<ClientVersionAgentId> dataPage) {

        Map<String, Object> param = JSONUtils.object2MapSpecail(option);
        param.put("startIndex", (dataPage.getPageNo() - 1) * dataPage.getPageSize());
        param.put("pageSize", dataPage.getPageSize());
        return dao.queryPage("com.mybatis.mapper.ClientVersionAgentIdMapper.countByCondition",
                "com.mybatis.mapper.ClientVersionAgentIdMapper.selectPageByCondition", param, dataPage);
    }


    @Override
    public List<ClientVersionAgentId> queryListByCondition(ClientVersionAgentIdOption option) {
        Map<String, Object> param = JSONUtils.object2MapSpecail(option);
        return dao.queryList("com.mybatis.mapper.ClientVersionAgentIdMapper.selectListByClientTypeClientTypeAgentId", param);
    }


    @Override
    public Boolean deleteByBusinessId(Integer businessId) {
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("businessId", businessId);
            int result = dao.update("com.mybatis.mapper.ClientVersionAgentIdMapper.deleteByBusinessId", param);
            if (result == 0) {
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        } catch (Exception e) {
            logger.error("com.mybatis.mapper.ClientVersionAgentIdMapper.deleteByBusinessId 报错：{}", e.getMessage(), e);
            return Boolean.FALSE;
        }
    }

    @Override
    public List<ClientVersionAgentId> queryListGroupByBusinessId(ClientVersionAgentIdOption option) {
        Map<String, Object> param = JSONUtils.object2MapSpecail(option);
        return dao.queryList("com.mybatis.mapper.ClientVersionAgentIdMapper.selectListByConditionGroupByBusinessId", param);
    }

    @Override
    public ModelResult<Boolean> deleteById(ClientVersionAgentIdOption option) {
        ClientVersionAgentIdOption tempOption = new ClientVersionAgentIdOption();
        tempOption.setBusinessId(option.getBusinessId());
        tempOption.setBusinessType(option.getBusinessType());
        List<ClientVersionAgentId> list = queryListByCondition(tempOption);
        if (list != null && list.size() <= 1) {
            return new ModelResult<>(Boolean.FALSE);
        }

        return deleteById(option.getClientVersionAgentId());
    }

    @Override
    public List<ClientVersionAgentId> queryList(ClientVersionAgentIdOption option) {
        Map<String, Object> param = JSONUtils.object2MapSpecail(option);
        return dao.queryList("com.mybatis.mapper.ClientVersionAgentIdMapper.selectListByCondition", param);
    }

}