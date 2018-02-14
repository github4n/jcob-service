package com.aicai.jcob.client.manager.impl;


import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericDao;
import com.aicai.jcob.client.common.domain.ClientUpgrade;
import com.aicai.jcob.client.manager.ClientUpgradeManager;
import com.aicai.jcob.common.constant.ClientType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClientUpgradeManagerImpl implements ClientUpgradeManager {

    private static final Logger logger = LoggerFactory.getLogger(ClientUpgradeManagerImpl.class);

    @Resource(name = "tjplanDbDao")
    private GenericDao dao;

    @Override
    public boolean insertClientUpgrade(ClientUpgrade clientUpgrade) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("curVersion", clientUpgrade.getCurVersion());
        paramMap.put("oldVersion", clientUpgrade.getOldVersion());
        paramMap.put("clientType", clientUpgrade.getClientType());
        paramMap.put("channel", clientUpgrade.getChannel());
        paramMap.put("model", clientUpgrade.getModel());
        boolean exist = dao.queryInt("ClientUpgradeMapper.countByVersion", paramMap) > 0;
        if (exist) {
            //如果是配置升级,出现重复就更新
            if (clientUpgrade.getModel() == 1) {
                return dao.updateByObj("ClientUpgradeMapper.updateByOldVerionChannel", clientUpgrade) > 0;
            }
            return false;
        }
        return dao.insertAndReturnAffectedCount("ClientUpgradeMapper.insertClientUpgrade", clientUpgrade) > 0;
    }

    @Override
    public ModelResult<ClientUpgrade> judgeNeedUpgrade(String oldVersion, ClientType clientType, Long channelId) {
        logger.info("版本号:{}, 客户端类型：{}, 渠道：{}", oldVersion, clientType, channelId);
        ClientUpgrade clientUpgrade = queryCurVersion(oldVersion, clientType.getIndex(), channelId);
        if (clientUpgrade == null) {
            logger.info("版本号:{}, 客户端类型：{}, 渠道：{}  找不到数据", oldVersion, clientType, channelId);
            return new ModelResult<>().withError("exception", "查询不到版本");
        }

        if (clientUpgrade.getCurVersion().equals(clientUpgrade.getOldVersion())) {
            logger.info("现在的版本号：{},旧版本号:{}", clientUpgrade.getCurVersion(), clientUpgrade.getOldVersion());
            return new ModelResult<>(null);
        }
        String [] curVersions = clientUpgrade.getCurVersion().split("\\.");
        String [] oldVersions = clientUpgrade.getOldVersion().split("\\.");
        for (int i=0; i<curVersions.length; i++){
            if(Integer.parseInt(curVersions[i]) > Integer.parseInt(oldVersions[i])){
                return new ModelResult<>(clientUpgrade);
            }
        }
        return new ModelResult<>(null);
    }

    @Override
    public ModelResult<ClientUpgrade> queryRecentVersion(ClientType clientType, Long channelId) {

        Map<String,Object> param = new HashMap<>();
        param.put("clientType", clientType.getIndex());
        param.put("channel", channelId);
        List<ClientUpgrade> list = dao.queryList("ClientUpgradeMapper.queryRecentVersion", param);
        if(list!=null && list.size()>0){
            return new ModelResult<>(list.get(0));
        }
        return new ModelResult<>(null);
    }

    @Override
    public boolean updateClientUpgrade(ClientUpgrade clientUpgrade) {
        return dao.updateByObj("ClientUpgradeMapper.updateClientUpgrade", clientUpgrade) > 0;
    }

    @Override
    public ClientUpgrade queryCurVersion(String oldVersion, Integer clientType, Long channelId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("oldVersion", oldVersion);
        paramMap.put("clientType", clientType);
        paramMap.put("channel", channelId);
        return dao.queryUnique("ClientUpgradeMapper.queryCurVersion", paramMap);
    }

    @Override
    public ClientUpgrade queryById(Long id) {
        return dao.queryUnique("ClientUpgradeMapper.selectByPrimaryKey", id);
    }

    @Override
    public List<String> queryAllVersion(Integer clientType) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("clientType", clientType);
        return dao.queryList("ClientUpgradeMapper.queryAllVersion", paramMap);
    }

    @Override
    public DataPage<ClientUpgrade> queryByPage(ClientUpgrade clientUpgrade, DataPage<ClientUpgrade> page) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", clientUpgrade.getId());
        map.put("curVersion", clientUpgrade.getCurVersion());
        map.put("oldVersion", clientUpgrade.getOldVersion());
        map.put("clientType", clientUpgrade.getClientType());
        map.put("channel", clientUpgrade.getChannel());
        map.put("model", clientUpgrade.getModel());
        map.put("startIndex", (page.getPageNo() - 1) * page.getPageSize());
        map.put("pageSize", page.getPageSize());
        return dao.queryPage("ClientUpgradeMapper.queryByPageCount", "ClientUpgradeMapper.queryByPage", map, page);
    }

    @Override
    public boolean delById(Long id) {
        return dao.updateByObj("ClientUpgradeMapper.delById", id) > 0;
    }

    @Override
    public List<Integer> queryAllChannel() {
        return dao.queryList("ClientUpgradeMapper.queryAllChannel");
    }


    @Override
    public ModelResult<List<ClientUpgrade>> queryAllVersionByClientType(Integer clientType) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("clientType", clientType);
        return new ModelResult<>(dao.queryList("ClientUpgradeMapper.selectAllVersion", paramMap));

    }
}
