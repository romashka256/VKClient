package com.client.vk.roma.vkclient.userprofile.view;

import com.client.vk.roma.vkclient.userprofile.models.User;
import com.vk.sdk.api.VKError;

/**
 * Created by Roma on 11.05.2017.
 */

public interface IUserProfileView {
    void onUserProfileLoadedSuccess(User user);
    void onUserProfileLoadedFailure(VKError error);
    void onUserProfileLoading();
    void setDataInViews(User user);
    void onBackPressed();
}
