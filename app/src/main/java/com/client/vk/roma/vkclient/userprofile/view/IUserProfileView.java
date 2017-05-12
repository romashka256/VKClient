package com.client.vk.roma.vkclient.userprofile.view;

import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKApiUserFull;

/**
 * Created by Roma on 11.05.2017.
 */

public interface IUserProfileView {
    void onUserProfileLoadedSuccess(VKApiUserFull vkApiUserFull);
    void onUserProfileLoadedFailure(VKError error);
}
