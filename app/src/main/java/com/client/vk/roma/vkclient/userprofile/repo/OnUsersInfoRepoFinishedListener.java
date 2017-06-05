package com.client.vk.roma.vkclient.userprofile.repo;

import com.vk.sdk.api.model.VKList;

/**
 * Created by Roma on 24.05.2017.
 */

public interface OnUsersInfoRepoFinishedListener {

    String onUserNameNetworkSuccess(VKList UserfullName);
    String onUserNameNetworkFailure();

}
