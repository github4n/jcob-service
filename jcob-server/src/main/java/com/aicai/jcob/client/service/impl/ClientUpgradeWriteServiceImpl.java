package com.aicai.jcob.client.service.impl;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.client.common.domain.ClientUpgrade;
import com.aicai.jcob.client.common.service.ClientUpgradeWriteService;
import com.aicai.jcob.client.manager.ClientUpgradeManager;
import com.aicai.jcob.common.constant.ClientType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ClientUpgradeWriteServiceImpl implements ClientUpgradeWriteService {
    protected final Logger logger = LoggerFactory.getLogger(ClientUpgradeWriteServiceImpl.class);
    @Autowired
    private ClientUpgradeManager clientUpgradeManager;

    @Override
    public ModelResult<ClientUpgrade> clientUpgrade(String version, Integer sellClient, Long channelId) {
        ModelResult<ClientUpgrade> result = new ModelResult<ClientUpgrade>();
        result.setReadFromCache(false);
        try {
            result.setModel(clientUpgradeManager.queryCurVersion(version, sellClient, channelId));
        } catch (Exception e) {
            result.withError("err", "查询升级包发生错误");
            logger.error("查询升级包发生错误", e);
        }
        return result;
    }

    @Override
    public ModelResult<Boolean> createClientUpgrade(ClientUpgrade clientUpgrade) {
        ModelResult<Boolean> result = new ModelResult<Boolean>();
        result.setReadFromCache(false);
        try {
            result.setModel(clientUpgradeManager.insertClientUpgrade(clientUpgrade));
        } catch (Exception e) {
            result.withError("err", "创建升级记录出错");
            logger.error("创建升级记录出错", e);
        }
        return result;
    }

    @Override
    public ModelResult<Boolean> updateClientUpgrade(ClientUpgrade clientUpgrade) {
        ModelResult<Boolean> result = new ModelResult<Boolean>();
        result.setReadFromCache(false);
        try {
            result.setModel(clientUpgradeManager.updateClientUpgrade(clientUpgrade));
        } catch (Exception e) {
            result.withError("err", "更新升级记录出错");
            logger.error("更新升级记录出错", e);
        }
        return result;
    }

    @Override
    public ModelResult<List<String>> queryAllVersion(Integer sellClient) {
        ModelResult<List<String>> result = new ModelResult<List<String>>();
        result.setReadFromCache(false);
        try {
            result.setModel(clientUpgradeManager.queryAllVersion(sellClient));
        } catch (Exception e) {
            result.withError("err", "查询所有版本号");
            logger.error("查询所有版本号", e);
        }
        return result;
    }


    @Override
    public ModelResult<List<ClientUpgrade>> queryAllVersionByClientType(Integer clientType) {
        return clientUpgradeManager.queryAllVersionByClientType(clientType);
    }

    @Override
    public PageResult<ClientUpgrade> queryClientUpgradeByPage(ClientUpgrade clientUpgrade, DataPage<ClientUpgrade> page) {
        PageResult<ClientUpgrade> pageResult = new PageResult<ClientUpgrade>();
        try {
            pageResult.setPage(clientUpgradeManager.queryByPage(clientUpgrade, page));
        } catch (Exception e) {
            pageResult.withError("err", "分页查询出错");
            logger.error("分页查询出", e);
        }
        return pageResult;
    }

    @Override
    public ModelResult<ClientUpgrade> queryClientUpgradeById(Long id) {
        ModelResult<ClientUpgrade> result = new ModelResult<ClientUpgrade>();
        result.setReadFromCache(false);
        try {
            result.setModel(clientUpgradeManager.queryById(id));
        } catch (Exception e) {
            result.withError("err", "按ID查询出现错误");
            logger.error("按ID查询出现错误", e);
        }
        return result;
    }

    @Override
    public ModelResult<List<Integer>> queryAllChannel() {
        ModelResult<List<Integer>> result = new ModelResult<List<Integer>>();
        try {
            result.setModel(clientUpgradeManager.queryAllChannel());
        } catch (Exception e) {
            result.withError("err", "查询所有渠道号出错");
            logger.error("查询所有渠道号出错", e);
        }
        return result;
    }

    @Override
    public ModelResult<Boolean> delClientUpgradeById(Long id) {
        ModelResult<Boolean> result = new ModelResult<Boolean>();
        try {
            result.setModel(clientUpgradeManager.delById(id));
        } catch (Exception e) {
            result.withError("err", "根据主键ID删除版本升级信息出现错误");
            logger.error("根据主键ID删除版本升级信息", e);
        }
        return result;
    }

    @Override
    public ModelResult<ClientUpgrade> judgeUpgrade(String version, ClientType clientType, Long channelId) {
        return clientUpgradeManager.judgeNeedUpgrade(version, clientType, channelId);
    }

    @Override
    public ModelResult<ClientUpgrade> queryRecentVersion(ClientType clientType, Long channelId) {
        return clientUpgradeManager.queryRecentVersion(clientType, channelId);
    }


}
