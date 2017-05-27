package com.client.vk.roma.vkclient.userprofile.presenter;

import com.client.vk.roma.vkclient.userprofile.repo.OnRequstsForUsersInfoRepoListener;
import com.client.vk.roma.vkclient.userprofile.repo.RequestsForUsersInfoRepo;
import com.client.vk.roma.vkclient.userprofile.view.IFriendsView;
import com.vk.sdk.api.model.VKApiUser;

/**
 * Created by Roma on 24.05.2017.
 */

public class UsersInfoPresenterListener implements IUsersInfoPresenter,OnRequstsForUsersInfoRepoListener {

    private RequestsForUsersInfoRepo requestsForUsersInfoRepo;
    private IFriendsView view;     //

    public UsersInfoPresenterListener(IFriendsView view) {               //
        this.view = view;
        requestsForUsersInfoRepo = new RequestsForUsersInfoRepo(this);
    }

    @Override
    public void loadNameOfUserById(int id) {

        requestsForUsersInfoRepo.getNameOfUserById(id);

    }

    @Override
    public String onUserNameNetworkSuccess(VKApiUser vkApiUserFullName) {

        return vkApiUserFullName.first_name + " " + vkApiUserFullName.last_name;

    }

    @Override
    public String onUserNameNetworkFailure(){

        return "Ошибка загрузки имени";

    }
}
