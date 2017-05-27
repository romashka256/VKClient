package com.client.vk.roma.vkclient.userprofile.repo;

import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKApiDialog;
import com.vk.sdk.api.model.VKList;

/**
 * Created by Roma on 22.05.2017.
 */

public interface OnDialogsRepoFinishedListener {
    void onDialogsNetworkSuccess(VKList<VKApiDialog> vkDialogResponse);
    void onDialogsNetworkLoading();
    void oDialogsNetworkFailure(VKError err1or);
}
