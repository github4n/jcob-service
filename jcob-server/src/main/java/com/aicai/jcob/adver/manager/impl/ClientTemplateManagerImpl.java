package com.aicai.jcob.adver.manager.impl;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericDao;
import com.aicai.jcob.adver.common.constant.BusinessType;
import com.aicai.jcob.adver.common.constant.DataType;
import com.aicai.jcob.adver.common.constant.ModuleStyle;
import com.aicai.jcob.adver.common.constant.UsingStatus;
import com.aicai.jcob.adver.common.domain.ClientModule;
import com.aicai.jcob.adver.common.domain.ClientModuleAd;
import com.aicai.jcob.adver.common.domain.ClientTemplate;
import com.aicai.jcob.adver.common.domain.ClientVersionAgentId;
import com.aicai.jcob.adver.common.domain.option.ClientModuleAdOption;
import com.aicai.jcob.adver.common.domain.option.ClientTemplateOption;
import com.aicai.jcob.adver.common.domain.option.ClientVersionAgentIdOption;
import com.aicai.jcob.adver.common.result.ClientTemplateData;
import com.aicai.jcob.adver.manager.ClientModuleAdManager;
import com.aicai.jcob.adver.manager.ClientModuleManager;
import com.aicai.jcob.adver.manager.ClientTemplateManager;
import com.aicai.jcob.adver.manager.ClientVersionAgentIdManager;
import com.aicai.jcob.common.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.*;

public class ClientTemplateManagerImpl implements ClientTemplateManager {

    private static final Logger logger = LoggerFactory.getLogger(ClientTemplateManagerImpl.class);

    @Resource(name = "tjplanDbDao")
    private GenericDao dao;

    @Resource(name = "clientVersionAgentIdManagerImpl")
    private ClientVersionAgentIdManager clientVersionAgentIdManager;

    @Resource
    private ClientModuleManager clientModuleManager;

    @Resource
    private ClientModuleAdManager clientModuleAdManager;


    @Override
    public int insert(ClientTemplate clientTemplate) {
        clientTemplate.setStatus(UsingStatus.stopping.getIndex());
        return dao.insertAndReturnAffectedCount("com.mybatis.mapper.ClientTemplateMapper.insert", clientTemplate);
    }

    @Override
    public ClientTemplate queryById(long id) {
        return dao.queryUnique("com.mybatis.mapper.ClientTemplateMapper.selectById", id);
    }

    @Override
    public int deleteById(long id) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        return dao.update("com.mybatis.mapper.ClientTemplateMapper.deleteById", param);
    }

    @Override
    public ModelResult<Boolean> deleteTemplateById(long id) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);

        ClientTemplate clientTemplate = queryById(id);
        if (clientTemplate.getStatus() == UsingStatus.using.getIndex()) {
            return new ModelResult<>(Boolean.FALSE).withError("exception", "使用中状态，不能删除");
        }
        try {

            //先要从Client_version_agentId表中删除
            clientVersionAgentIdManager.deleteByBusinessId(clientTemplate.getId());

            int result = dao.update("com.mybatis.mapper.ClientTemplateMapper.deleteById", param);
            if(result ==1){
                return new ModelResult<>(Boolean.TRUE);
            }
            return new ModelResult<>().withError("exception", "删除数据失败");
        }catch (Exception e){
            logger.error("com.mybatis.mapper.ClientTemplateMapper.deleteById 报错:{}" , e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }

    }

    @Override
    public List<ClientTemplate> queryList(ClientTemplateOption option) {
        Map<String, Object> param = JSONUtils.object2MapSpecail(option);
        return dao.queryList("com.mybatis.mapper.ClientTemplateMapper.selectListByCondition", param);
    }

    @Override
    public DataPage<ClientTemplate> queryPage(Long clientType, Integer pageId, DataPage<ClientTemplate> dataPage) {
        Map<String, Object> param = new HashMap<>();
        if (clientType != null) {
            param.put("clientType", clientType);
        }
        if (pageId != null) {
            param.put("pageId", pageId);
        }

        param.put("startIndex", (dataPage.getPageNo() - 1) * dataPage.getPageSize());
        param.put("pageSize", dataPage.getPageSize());

        return dao.queryPage("com.mybatis.mapper.ClientTemplateMapper.countByCondition",
                "com.mybatis.mapper.ClientTemplateMapper.selectPageByCondition", param, dataPage);
    }

    @Override
    public DataPage<ClientTemplate> queryPage(ClientTemplateOption option, DataPage<ClientTemplate> dataPage) {
        ClientVersionAgentIdOption clientVersionAgentIdOption = new ClientVersionAgentIdOption();
        clientVersionAgentIdOption.setAgentId(option.getAgentIdInt());
        clientVersionAgentIdOption.setClientVersion(option.getClientVersion());
        clientVersionAgentIdOption.setBusinessType(BusinessType.template.getIndex());
        clientVersionAgentIdOption.setClientType(option.getClientType());
        List<ClientVersionAgentId> list = clientVersionAgentIdManager.queryListGroupByBusinessId(clientVersionAgentIdOption);
        List<Integer> ids = new ArrayList<>();
        for (ClientVersionAgentId item : list) {
            ids.add(item.getBusinessId());
        }
        Map<String, Object> param = new HashMap<>();
        param.put("ids", ids);
        param.putAll(JSONUtils.object2MapSpecail(option));
        dao.queryPage("com.mybatis.mapper.ClientTemplateMapper.countByCondition",
                "com.mybatis.mapper.ClientTemplateMapper.selectPageByCondition", param, dataPage);
        return dataPage;
    }

    @Override
    public ModelResult<Boolean> addTemplateAndVersionAndAgentId(ClientTemplateOption option) {
        ClientTemplate clientTemplate = new ClientTemplate();
        clientTemplate.setClientType(option.getClientType());
        clientTemplate.setPageId(option.getPageId());
        clientTemplate.setStatus(option.getStatus());
        clientTemplate.setTemplateName(option.getTemplateName());
        clientTemplate.setUpdateUser(option.getUpdateUser());
        clientTemplate.setUpdateDate(Calendar.getInstance());

        try {
            int result = insert(clientTemplate);
            if (result == 0) {
                return new ModelResult<>().withError("exception", "模版插入不成功");
            }

            ClientVersionAgentId clientVersionAgentId = null;
            List<Integer> agentIds = option.getAgentIds();
            for (Integer agentId : agentIds){
                clientVersionAgentId = new ClientVersionAgentId();
                clientVersionAgentId.setAgentId(agentId);
                clientVersionAgentId.setBusinessId(clientTemplate.getId());
                clientVersionAgentId.setBusinessType(BusinessType.template.getIndex());
                clientVersionAgentId.setClientVersion(option.getClientVersion());
                clientVersionAgentId.setClientType(option.getClientType());
                clientVersionAgentIdManager.insert(clientVersionAgentId);
            }
        } catch (Exception e) {
            logger.error("插入模版和版本和渠道 异常:{}", e.getMessage());
            return new ModelResult<>().withError("exception", "插入模版和版本和渠道 异常");
        }
        return new ModelResult<>(Boolean.TRUE);
    }

    @Override
    public ModelResult<Boolean> updateTemplate(ClientTemplate template) {
        try {
            //如果将模版修改为使用中 需要先判断是不是有内容
            ClientTemplate clientTemplate = queryById(template.getId());
            if(clientTemplate.getStatus() == UsingStatus.stopping.getIndex() && template.getStatus() == UsingStatus.using.getIndex()){
                int size = clientModuleAdManager.isExsitAvailableModuleAd(clientTemplate.getId());
                if(size == 0){
                    logger.error("这个模版:{}:没有有效的广告位");
                    return new ModelResult<>().withError("exception", "这个模版中没有有效的广告位");
                }
            }

            template.setUpdateDate(Calendar.getInstance());
            Map<String, Object> para = JSONUtils.object2MapSpecail(template);
            int result = dao.update("com.mybatis.mapper.ClientTemplateMapper.updateTemplate", para);
            if (result == 1) {
                return new ModelResult<>(Boolean.TRUE);
            }
            return new ModelResult<>(Boolean.FALSE);
        } catch (Exception e) {
            logger.error("更新模版{}:数据异常：{}", template.getId(), e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public ModelResult<List<ClientTemplateData>> queryContent(Integer clientType, String version, Integer agentId, Integer position) {
        logger.info("ClientTemplateManagerImpl.queryContent() clientType:{},version:{},agentId:{},position:{}", clientType, version, agentId, position);

        ClientVersionAgentIdOption clientOption = new ClientVersionAgentIdOption(BusinessType.template.getIndex(), clientType, version, null, agentId);
        List<ClientVersionAgentId> clientVersionAgentIdList = clientVersionAgentIdManager.queryListByCondition(clientOption);

        if(clientVersionAgentIdList == null || clientVersionAgentIdList.isEmpty()){
            logger.info("前端根据条件：clientType:{},version:{},agentId:{}, 状态:{} 查找不到数据", clientType, version, agentId);
            return new ModelResult<>().withError("exception", "查询不到数据");
        }

        List<Integer> templateIds = new ArrayList<>();
        for (ClientVersionAgentId item: clientVersionAgentIdList){
            templateIds.add(item.getBusinessId());
        }

        ClientTemplateOption option = new ClientTemplateOption();
        option.setPageId(position);
        option.setStatus(UsingStatus.using.getIndex());
        option.setIds(templateIds);
        List<ClientTemplate> clientTemplateList = queryList(option);
        if (clientTemplateList == null || clientTemplateList.isEmpty()) {
            logger.info("根据模版id:{} 位置:{} 找不到模版", templateIds, position);
            return new ModelResult<>().withError("notFind", "找不到模版");
        }

        if(clientTemplateList.size()>1){
            logger.info("根据模版id:{} 位置:{} 查到找多个模版", clientType, version, agentId, clientTemplateList.size());
            return new ModelResult<>().withError("FindMulti", "查找到多个模版");
        }

        List<ClientModule> clientModuleList = clientModuleManager.queryList(clientTemplateList.get(0).getId(), UsingStatus.using.getIndex());
        List<ClientModuleAd> clientModuleAdList = null ;
        ClientModuleAdOption clientModuleAdOption = null ;
        List<ClientTemplateData> resultList = new ArrayList<>();
        ClientTemplateData clientTemplateData = null ;
        for (ClientModule item : clientModuleList) {
            if(item.getModuleStyle() == ModuleStyle.tuijiandan.getIndex() && item.getDataType() != DataType.person.getIndex()){
                clientTemplateData = new ClientTemplateData();
                clientTemplateData.setClientModule(item);
                resultList.add(clientTemplateData);
                continue;
            }
            clientModuleAdOption = new ClientModuleAdOption(item.getId(), UsingStatus.using.getIndex());
            clientModuleAdOption.setNeedTime(1);
            clientModuleAdList = clientModuleAdManager.queryList(clientModuleAdOption);
            clientTemplateData = new ClientTemplateData();
            clientTemplateData.setClientModule(item);
            clientTemplateData.setClientModuleAdList(clientModuleAdList);
            resultList.add(clientTemplateData);
        }

        return new ModelResult<>(resultList);
    }
}