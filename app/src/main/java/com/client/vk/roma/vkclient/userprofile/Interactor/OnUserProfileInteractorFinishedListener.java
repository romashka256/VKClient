package com.client.vk.roma.vkclient.userprofile.Interactor;

import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKApiUserFull;

/**
 * Created by Roma on 11.05.2017.
 */

public interface OnUserProfileInteractorFinishedListener {
    void onNetworkSuccess(VKApiUserFull vkApiUserFull);
    void onNetworkFailure(VKError error);
}
