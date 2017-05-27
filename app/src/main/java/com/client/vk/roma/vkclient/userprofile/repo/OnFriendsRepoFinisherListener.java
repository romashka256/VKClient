package com.client.vk.roma.vkclient.userprofile.repo;

import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKList;

/**
 * Created by Roma on 23.05.2017.
 */

public interface OnFriendsRepoFinisherListener {

    void onFriendsNetworkSuccess(VKList vkDialogResponse);
    void onFriendsNetworkLoading();
    void onFriendsNetworkFailure(VKError err1or);
}
