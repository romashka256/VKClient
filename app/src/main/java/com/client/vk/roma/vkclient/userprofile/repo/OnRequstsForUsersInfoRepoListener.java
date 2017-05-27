package com.client.vk.roma.vkclient.userprofile.repo;

import com.vk.sdk.api.model.VKApiUser;

/**
 * Created by Roma on 24.05.2017.
 */

public interface OnRequstsForUsersInfoRepoListener {

    String onUserNameNetworkSuccess(VKApiUser UserfullName);
    String onUserNameNetworkFailure();

}
