package com.client.vk.roma.vkclient.userprofile.presenter;

import com.client.vk.roma.vkclient.User;
import com.client.vk.roma.vkclient.userprofile.repo.OnUserProfileRepoFinishedListener;
import com.client.vk.roma.vkclient.userprofile.repo.UserProfileRepoAsync;
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
    private UserProfileRepoAsync profileRepo;
    private JSONHelper jsonHelper;
    private int GET_PROFILE_TYPE = 0;


    public UserProfilePresenter(IUserProfileView view) {
        this.view = view;
        this.profileRepo = new UserProfileRepoAsync(GET_PROFILE_TYPE,this);
        jsonHelper = new JSONHelper();
    }

    @Override
    public void loadInfo() {
        profileRepo = new UserProfileRepoAsync(GET_PROFILE_TYPE,this);
        profileRepo.execute();
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
