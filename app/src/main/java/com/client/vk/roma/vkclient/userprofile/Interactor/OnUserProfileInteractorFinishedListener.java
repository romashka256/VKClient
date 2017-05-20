package com.client.vk.roma.vkclient.userprofile.Interactor;

import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKApiUser;

import org.json.JSONObject;

/**
 * Created by Roma on 11.05.2017.
 */

public interface OnUserProfileInteractorFinishedListener {
    void onNetworkSuccess(VKApiUser vkApiUserList,JSONObject vkApiFriends,JSONObject vkApiPhotos);
    void onNetworkLoading();
    void onNetworkFailure(VKError error);
}
