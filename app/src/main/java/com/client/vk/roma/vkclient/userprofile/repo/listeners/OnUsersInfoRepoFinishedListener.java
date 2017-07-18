package com.client.vk.roma.vkclient.userprofile.repo.listeners;

import org.json.JSONObject;

/**
 * Created by Roma on 24.05.2017.
 */

public interface OnUsersInfoRepoFinishedListener {

    void onUserNameNetworkSuccess(JSONObject UserfullName);
    void onUserNameNetworkFailure();

}
