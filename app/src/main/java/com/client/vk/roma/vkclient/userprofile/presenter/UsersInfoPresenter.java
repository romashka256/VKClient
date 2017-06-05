package com.client.vk.roma.vkclient.userprofile.presenter;

import com.client.vk.roma.vkclient.userprofile.JSONHelper;
import com.client.vk.roma.vkclient.userprofile.repo.OnUsersInfoRepoFinishedListener;
import com.client.vk.roma.vkclient.userprofile.repo.UsersInfoRepo;
import com.vk.sdk.api.model.VKList;

/**
 * Created by Roma on 24.05.2017.
 */

public class UsersInfoPresenter implements IUsersInfoPresenter,OnUsersInfoRepoFinishedListener {

    private UsersInfoRepo usersInfoRepo;
    private JSONHelper helper;
    private String name;

    public UsersInfoPresenter() {
        usersInfoRepo = new UsersInfoRepo(this);
        helper = new JSONHelper();
    }

    public String getName(){
        return name;
    }

    @Override
    public void loadNameOfUserById(int id) {
        usersInfoRepo.getNameOfUserById(id);
    }

    @Override
    public String onUserNameNetworkSuccess(VKList vkApiUserFullName) {
        name = helper.getNameFromJson(vkApiUserFullName);
        return name;
    }

    @Override
    public String onUserNameNetworkFailure(){
        return "Ошибка загрузки имени";
    }
}
