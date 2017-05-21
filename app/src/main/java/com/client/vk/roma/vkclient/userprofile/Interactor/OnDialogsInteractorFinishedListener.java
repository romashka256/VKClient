package com.client.vk.roma.vkclient.userprofile.Interactor;

import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKApiUser;

import org.json.JSONObject;

/**
 * Created by Roma on 22.05.2017.
 */

public interface OnDialogsInteractorFinishedListener {
    void onDialogsNetworkSuccess(VKApiUser vkApiUserList, JSONObject vkApiFriends, JSONObject vkApiPhotos);
    void onDialogsNetworkLoading();
    void oDialogsNetworkFailure(VKError error);

}
