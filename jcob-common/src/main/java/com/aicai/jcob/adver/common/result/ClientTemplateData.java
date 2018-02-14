package com.aicai.jcob.adver.common.result;

import com.aicai.jcob.adver.common.domain.ClientModule;
import com.aicai.jcob.adver.common.domain.ClientModuleAd;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tianlun.wu on 2016/2/24 0024.
 */
public class ClientTemplateData implements Serializable{

    private static final long serialVersionUID = -712155271162203511L;

    private ClientModule clientModule;

    private List<ClientModuleAd> clientModuleAdList;

    public ClientModule getClientModule() {
        return clientModule;
    }

    public void setClientModule(ClientModule clientModule) {
        this.clientModule = clientModule;
    }

    public List<ClientModuleAd> getClientModuleAdList() {
        return clientModuleAdList;
    }

    public void setClientModuleAdList(List<ClientModuleAd> clientModuleAdList) {
        this.clientModuleAdList = clientModuleAdList;
    }
}
