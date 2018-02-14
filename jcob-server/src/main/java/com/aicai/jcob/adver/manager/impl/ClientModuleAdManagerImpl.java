package com.aicai.jcob.adver.manager.impl;


import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericDao;
import com.aicai.jcob.adver.common.constant.UsingStatus;
import com.aicai.jcob.adver.common.domain.ClientModuleAd;
import com.aicai.jcob.adver.common.domain.option.ClientModuleAdOption;
import com.aicai.jcob.adver.manager.ClientModuleAdManager;
import com.aicai.jcob.common.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientModuleAdManagerImpl implements ClientModuleAdManager {

    private static final Logger logger = LoggerFactory.getLogger(ClientModuleAdManagerImpl.class);

    @Resource(name = "tjplanDbDao")
    private GenericDao dao;


    @Override
    public ModelResult<Boolean> insert(ClientModuleAd clientModuleAd) {
        try {
            Calendar nowTime = Calendar.getInstance();
            nowTime.add(Calendar.MONTH, -1);
            if(clientModuleAd.getStartDate() == null ){
                clientModuleAd.setStartDate(nowTime);
            }
            nowTime = Calendar.getInstance();
            nowTime.add(Calendar.YEAR, 5);
            if(clientModuleAd.getEndDate() == null){
                clientModuleAd.setEndDate(nowTime);
            }

            ClientModuleAd resultAd = queryByModuleIdAndModulePosition(clientModuleAd.getModuleId(), clientModuleAd.getModulePosition());
            if (resultAd != null) {
                return new ModelResult<>().withError("exception", "广告位置已经被占领了");
            }

            clientModuleAd.setUpdateDate(Calendar.getInstance());
            int result = dao.insertAndReturnAffectedCount("com.mybatis.mapper.ClientModuleAdMapper.insert", clientModuleAd);
            if (result == 1) {
                return new ModelResult<>(Boolean.TRUE);
            }
            return new ModelResult<>().withError("exception", "插入失败");
        } catch (Exception e) {
            logger.error("com.mybatis.mapper.ClientModuleAdMapper.insert 报错{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public ClientModuleAd queryById(long id) {
        return dao.queryUnique("com.mybatis.mapper.ClientModuleAdMapper.selectById", id);
    }

    @Override
    public ModelResult<Boolean> deleteById(long id) {
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("id", id);
            int result = dao.update("com.mybatis.mapper.ClientModuleAdMapper.deleteById", param);
            if (result == 1) {
                return new ModelResult<>(Boolean.TRUE);
            }
            return new ModelResult<>().withError("exception", "删除失败");
        } catch (Exception e) {
            logger.error("com.mybatis.mapper.ClientModuleAdMapper.deleteById 报错:{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public List<ClientModuleAd> queryList(ClientModuleAdOption option) {
        Map<String, Object> param = JSONUtils.object2MapSpecail(option);
        param.put("nowTime", Calendar.getInstance());
        List<ClientModuleAd> list = dao.queryList("com.mybatis.mapper.ClientModuleAdMapper.selectListByCondition", param);
        return list;
    }

    @Override
    public DataPage<ClientModuleAd> queryPage(ClientModuleAdOption option, DataPage<ClientModuleAd> dataPage) {
        Map<String, Object> param = JSONUtils.object2MapSpecail(option);
        param.put("startIndex", (dataPage.getPageNo() - 1) * dataPage.getPageSize());
        param.put("pageSize", dataPage.getPageSize());
        if(option.getNeedTime() !=null && option.getNeedTime() == 1){
            param.put("nowTime", Calendar.getInstance());
        }
        return dao.queryPage("com.mybatis.mapper.ClientModuleAdMapper.countByCondition",
                "com.mybatis.mapper.ClientModuleAdMapper.selectPageByCondition", param, dataPage);
    }

    @Override
    public ClientModuleAd queryByModuleIdAndModulePosition(Integer moduleId, Integer position) {
        Map<String, Object> param = new HashMap<>();
        param.put("moduleId", moduleId);
        param.put("position", position);

        return dao.queryUnique("com.mybatis.mapper.ClientModuleAdMapper.selectByModuleIdAndModulePosition", param);
    }

    @Override
    public ModelResult<Boolean> updateModuleAdStatus(Integer id, String account) {
        try {
            ClientModuleAd clientModuleAd = queryById(id);
            if (clientModuleAd.getStatus() == UsingStatus.using.getIndex()) {
                clientModuleAd.setStatus(UsingStatus.stopping.getIndex());
            } else {
                clientModuleAd.setStatus(UsingStatus.using.getIndex());
            }

            clientModuleAd.setUpdateDate(Calendar.getInstance());
            clientModuleAd.setUpdateUser(account);

            int result = dao.updateByObj("com.mybatis.mapper.ClientModuleAdMapper.updateModuleAdStatus", clientModuleAd);
            if (result == 1) {
                return new ModelResult<>(Boolean.TRUE);
            }
            return new ModelResult<>().withError("exception", "更新失败");
        } catch (Exception e) {
            logger.error("com.mybatis.mapper.ClientModuleAdMapper.updateModuleAdStatus 报错:{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }

    @Override
    public ModelResult<Boolean> updateModuleAd(ClientModuleAd clientModuleAd) {
        try {
            Calendar nowTime = Calendar.getInstance();
            nowTime.add(Calendar.MONTH, -1);
            if(clientModuleAd.getStartDate() == null ){
                clientModuleAd.setStartDate(nowTime);
            }
            nowTime = Calendar.getInstance();
            nowTime.add(Calendar.YEAR, 5);
            if(clientModuleAd.getEndDate() == null){
                clientModuleAd.setEndDate(nowTime);
            }

            int result = dao.updateByObj("com.mybatis.mapper.ClientModuleAdMapper.update", JSONUtils.object2MapSpecail(clientModuleAd));
            if(result==1){
                return new ModelResult<>(Boolean.TRUE);
            }
            return new ModelResult<>().withError("exception", "更新失败");
        } catch (Exception e) {
            logger.error("调用 com.mybatis.mapper.ClientModuleAdMapper.update 报错:{}", e.getMessage(), e);
            return new ModelResult<>().withError("exception", e.getMessage());
        }
    }


    @Override
    public Integer isExsitAvailableModuleAd(long templateId) {
        Map<String, Object> param = new HashMap<>();
        param.put("nowTime", Calendar.getInstance());
        param.put("templateId", templateId);
        return dao.queryInt("com.mybatis.mapper.ClientModuleAdMapper.isExsitAvailableModuleAd", param);
    }
}