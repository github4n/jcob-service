package com.aicai.jcob.client.manager;


import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.client.common.domain.ClientUpgrade;
import com.aicai.jcob.common.constant.ClientType;

import java.util.List;

public interface ClientUpgradeManager {

    public ClientUpgrade queryCurVersion(String oldVersion, Integer clientType, Long channelId);

    /**
     * 是否升级
     * @param oldVersion
     * @param clientType
     * @param channelId
     * @return
     */
    ModelResult<ClientUpgrade> judgeNeedUpgrade(String oldVersion, ClientType clientType, Long channelId);

    ModelResult<ClientUpgrade> queryRecentVersion(ClientType clientTypem ,Long channelId);

    public boolean insertClientUpgrade(ClientUpgrade clientUpgrade);

    public boolean updateClientUpgrade(ClientUpgrade clientUpgrade);

    public ClientUpgrade queryById(Long id);
    
    public boolean delById(Long id);
    
    public List<String> queryAllVersion(Integer sellClient);

    public List<Integer> queryAllChannel();
    
    public DataPage<ClientUpgrade> queryByPage(ClientUpgrade clientUpgrade, DataPage<ClientUpgrade> page);

    public ModelResult<List<ClientUpgrade>> queryAllVersionByClientType(Integer clientType);

}
