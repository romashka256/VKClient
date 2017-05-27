package com.client.vk.roma.vkclient.userprofile.view;

import com.vk.sdk.api.VKError;

/**
 * Created by Roma on 24.05.2017.
 */

public interface IUsersInfoView {
    void onUserNameByIdLoadedSuccess(String name);
    void onUserNameByIdLoadingFailure(VKError error);
}
