package com.client.vk.roma.vkclient.userprofile.presenter;

import com.client.vk.roma.vkclient.User;
import com.client.vk.roma.vkclient.userprofile.repo.OnUserProfileRepoFinishedListener;
import com.client.vk.roma.vkclient.userprofile.repo.UserProfileRepo;
import com.client.vk.roma.vkclient.userprofile.JSONHelper;
import com.client.vk.roma.vkclient.userprofile.view.IUserProfileView;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKApiUser;

import org.json.JSONObject;

/**
 * Created by Roma on 11.05.2017.
 */

public class UserProfilePresenter implements  IUserProfilePresenter,OnUserProfileRepoFinishedListener {

    private User user;
    private IUserProfileView view;
    private UserProfileRepo profileInterator;
    private JSONHelper jsonHelper;


    public UserProfilePresenter(IUserProfileView view) {
        this.view = view;
        this.profileInterator = new UserProfileRepo(this);
        jsonHelper = new JSONHelper();
    }

    @Override
    public void loadInfo() {
        profileInterator.getProfileInfo();
    }

    @Override
    public void onUserProfileNetworkSuccess(VKApiUser vkApiUserList, JSONObject vkApiFriends, JSONObject vkApiPhotos) {
        user = jsonHelper.getUserFromJson(vkApiUserList, vkApiFriends,vkApiPhotos);
        view.onUserProfileLoadedSuccess(user);
    }

    @Override
    public void onUserProfileNetworkLoading() {

        view.onUserProfileLoading();
    }

    @Override
    public void onUserProfileNetworkFailure(VKError error) {

        view.onUserProfileLoadedFailure(error);
    }
}
