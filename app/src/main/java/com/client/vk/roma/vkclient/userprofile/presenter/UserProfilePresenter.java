package com.client.vk.roma.vkclient.userprofile.presenter;

import com.client.vk.roma.vkclient.userprofile.Interactor.OnUserProfileInteractorFinishedListener;
import com.client.vk.roma.vkclient.userprofile.Interactor.UserProfileInterator;
import com.client.vk.roma.vkclient.userprofile.view.IUserProfileView;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKApiUserFull;

/**
 * Created by Roma on 11.05.2017.
 */

public class UserProfilePresenter implements  IUserProfilePresenter,OnUserProfileInteractorFinishedListener {

    private IUserProfileView view;
    private UserProfileInterator interator;

    public UserProfilePresenter(IUserProfileView view) {
        this.view = view;
        this.interator = new UserProfileInterator(this);
    }

    @Override
    public void loadInfo() {
        interator.getProfileInfo();
    }

    @Override
    public void onNetworkSuccess(VKApiUserFull vkApiUserFull) {
        view.onUserProfileLoadedSuccess(vkApiUserFull);
    }

    @Override
    public void onNetworkFailure(VKError error) {
        view.onUserProfileLoadedFailure(error);

    }
}
