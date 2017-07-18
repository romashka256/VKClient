package com.client.vk.roma.vkclient.userprofile.view;

import com.client.vk.roma.vkclient.userprofile.models.Friend;
import com.vk.sdk.api.VKError;

/**
 * Created by Roma on 23.05.2017.
 */

public interface IFriendsView {
    void onFriendsLoadedSuccess(Friend friend);
    void onFriendsLoadFailure(VKError error);
    void onFriendsLoading();
}
