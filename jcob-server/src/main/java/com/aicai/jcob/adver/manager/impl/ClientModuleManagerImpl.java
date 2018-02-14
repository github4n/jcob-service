package com.aicai.jcob.adver.manager.impl;


import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericDao;
import com.aicai.jcob.adver.common.constant.UsingStatus;
import com.aicai.jcob.adver.common.domain.ClientModule;
import com.aicai.jcob.adver.manager.ClientModuleManager;
import com.aicai.jcob.common.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientModuleManagerImpl implements ClientModuleManager {

    private static final Logger logger = LoggerFactory.getLogger(ClientModuleManagerImpl.class);

    @Resource(name = "tjplanDbDao")
    private GenericDao dao;

    @Override
    public int insert(ClientModule clientModule) {
        int count = this.countModuleByTemplateId(clientModule.getTemplateId());
        clientModule.setTopNumber(count + 1);
        clientModule.setUpdateDate(Calendar.getInstance());
        return dao.insertAndReturnAffectedCount("com.mybatis.mapper.ClientModuleMapper.insert", clientModule);
    }

    @Override
    public ClientModule queryById(long id) {
        return dao.queryUnique("com.mybatis.mapper.ClientModuleMapper.selectById", id);
    }

    @Override
    public ModelResult<Boolean> update(ClientModule clientModule) {
        clientModule.setUpdateDate(Calendar.getInstance());
        Map<String, Object> param = JSONUtils.object2MapSpecail(clientModule);
        int size = dao.update("com.mybatis.mapper.ClientModuleMapper.update", param);
        if(size==1){
            return new ModelResult<>(Boolean.TRUE);
        }
        return new ModelResult<>(Boolean.FALSE);
    }

    @Override
    public int countModuleByTemplateId(Integer templateId) {
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("templateId", templateId);
        return dao.queryCount("com.mybatis.mapper.ClientModuleMapper.countModuleByTemplateId", paraMap);
    }

    @Override
    public ModelResult<Boolean> deleteById(long id) {

        try {
            ClientModule clientModule = queryById(id);
            if (clientModule.getStatus() == UsingStatus.using.getIndex()) {
                return new ModelResult<>().withError("exception", "正在使用中不能删除");
            }
            Map<String, Object> param = new HashMap<>();
            param.put("id", id);
            int result = dao.update("com.mybatis.mapper.ClientModuleMapper.deleteById", param);
            if (result == 1) {
                return new ModelResult<>(Boolean.TRUE);
            }
            return new ModelResult<>(Boolean.FALSE);
        } catch (Exception e) {
            logger.error("com.mybatis.mapper.ClientModuleMapper.deleteById 报错：{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public List<ClientModule> queryList(long templateId, int status) {
        Map<String, Object> param = new HashMap<>();
        param.put("templateId", templateId);
        param.put("status", status);
        return dao.queryList("com.mybatis.mapper.ClientModuleMapper.selectByCondition", param);
    }

    @Override
    public List<ClientModule> queryListUnionClientTemplate(long templateId, int status, Integer position) {
        Map<String, Object> param = new HashMap<>();
        param.put("templateId", templateId);
        param.put("status", status);
        param.put("pageId", position);
        return dao.queryList("com.mybatis.mapper.ClientModuleMapper.selectByUnionClientTemplate", param);
    }

    @Override
    public List<ClientModule> queryList(long templateId) {
        Map<String, Object> param = new HashMap<>();
        param.put("templateId", templateId);
        return dao.queryList("com.mybatis.mapper.ClientModuleMapper.selectByCondition", param);
    }

    @Override
    public DataPage<ClientModule> queryPage(long templateId, DataPage<ClientModule> dataPage) {
        Map<String, Object> param = new HashMap<>();
        param.put("templateId", templateId);
        param.put("startIndex", (dataPage.getPageNo() - 1) * dataPage.getPageSize());
        param.put("pageSize", dataPage.getPageSize());
        return dao.queryPage("com.mybatis.mapper.ClientModuleMapper.countByCondition",
                "com.mybatis.mapper.ClientModuleMapper.selectPageByCondition", param, dataPage);
    }

    @Override
    public ModelResult<Boolean> saveSort(List<Integer> ids, Integer templateId, String account) {
        Map<String, Object> param = null;
        try {
            for (int i = 1; i <= ids.size(); i++) {
                param = new HashMap<>();
                param.put("id", ids.get(i - 1));
                param.put("sort", i);
                param.put("templateId", templateId);
                param.put("updateUser", account);
                param.put("updateDate", Calendar.getInstance());

                dao.update("com.mybatis.mapper.ClientModuleMapper.updateSort", param);
            }

            return new ModelResult<>(Boolean.TRUE);
        } catch (Exception e) {
            logger.error("com.mybatis.mapper.ClientModuleMapper.updateSort 错误{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public ModelResult<Boolean> updateStatusByModuleId(long id, String account) {
        try {
            ClientModule clientModule = queryById(id);
            if(clientModule.getStatus() == UsingStatus.using.getIndex()){
                clientModule.setStatus(UsingStatus.stopping.getIndex());
            } else {
                clientModule.setStatus(UsingStatus.using.getIndex());
            }
            clientModule.setUpdateDate(Calendar.getInstance());
            clientModule.setUpdateUser(account);
            int result =dao.updateByObj("com.mybatis.mapper.ClientModuleMapper.updateStatus", clientModule);
            if(result == 1){
                return new ModelResult<>(Boolean.TRUE);
            }
            return new ModelResult<>().withError("exception", "更新失败");
        } catch (Exception e) {
            logger.error("com.mybatis.mapper.ClientModuleMapper.updateStatus 报错:{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }
}