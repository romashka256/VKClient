package com.client.vk.roma.vkclient.userprofile.Interactor;

import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKApiUser;

import org.json.JSONObject;

/**
 * Created by Roma on 11.05.2017.
 */

public interface OnUserProfileInteractorFinishedListener {
    void onUserProfileNetworkSuccess(VKApiUser vkApiUserList, JSONObject vkApiFriends, JSONObject vkApiPhotos);
    void onUserProfileNetworkLoading();
    void onUserProfileNetworkFailure(VKError error);
}
