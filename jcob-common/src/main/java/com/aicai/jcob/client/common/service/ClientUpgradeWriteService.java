package com.aicai.jcob.client.common.service;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.client.common.domain.ClientUpgrade;
import com.aicai.jcob.common.constant.ClientType;


import java.util.List;

public interface ClientUpgradeWriteService {

    /**
     * 客户端升级
     * @param version 当前客户使用的版本号
     * @param sellClient 平台类型,参考com.woqutz.didi.constants.SellClient
     * @return 
     */
    ModelResult<ClientUpgrade> clientUpgrade(String version, Integer sellClient, Long channelId);

    /**
     * 添加版本升级信息
     * @param clientUpgrade
     * @return
     */
    ModelResult<Boolean> createClientUpgrade(ClientUpgrade clientUpgrade);

    /**
     * 更新版本升级信息
     * @param clientUpgrade
     * @return
     */
    ModelResult<Boolean> updateClientUpgrade(ClientUpgrade clientUpgrade);

    /**
     * 查询所有版本号
     * @param sellClient
     * @return
     */
    ModelResult<List<String>> queryAllVersion(Integer sellClient);

    /**
     * 查询符合条件的版本号
     * 返回 clientUpgrade实体类集合
     * @param clientType
     * @return
     */
    ModelResult<List<ClientUpgrade>> queryAllVersionByClientType(Integer clientType);

    /**
     * 查询所有渠道号
     * @return
     */
    ModelResult<List<Integer>> queryAllChannel();

    /**
     * 根据主键ID查询版本升级信息
     * @param id
     * @return
     */
    ModelResult<ClientUpgrade> queryClientUpgradeById(Long id);

    /**
     * 根据主键ID删除版本升级信息
     * @param id
     * @return
     */
    ModelResult<Boolean> delClientUpgradeById(Long id);

    /**
     * 分页查询
     * @param clientUpgrade
     * @param page
     * @return
     */
    PageResult<ClientUpgrade> queryClientUpgradeByPage(ClientUpgrade clientUpgrade, DataPage<ClientUpgrade> page);

    /**
     * 判断是否需要升级
     * @param version
     * @param clientType
     * @param channelId
     * @return
     * 返回 null  则不需要升级
     * 不为null 需要升级
     */
    ModelResult<ClientUpgrade> judgeUpgrade(String version, ClientType clientType, Long channelId);

    /**
     * 查询某个渠道下面最新的版本
     * @param clientType
     * @param channelId
     * @return
     *  返回为null  则没有值
     *  返回有值 即可
     */
    ModelResult<ClientUpgrade> queryRecentVersion(ClientType clientType, Long channelId);
}
