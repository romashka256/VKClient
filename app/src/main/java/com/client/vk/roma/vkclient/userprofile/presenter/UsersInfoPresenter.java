package com.client.vk.roma.vkclient.userprofile.presenter;

import com.client.vk.roma.vkclient.userprofile.JSONHelper;
import com.client.vk.roma.vkclient.userprofile.repo.OnUsersInfoRepoFinishedListener;
import com.client.vk.roma.vkclient.userprofile.repo.UsersInfoRepoAsync;

import org.json.JSONObject;

/**
 * Created by Roma on 24.05.2017.
 */

public class UsersInfoPresenter implements IUsersInfoPresenter,OnUsersInfoRepoFinishedListener {

    private UsersInfoRepoAsync usersInfoRepoAsync;
    private JSONHelper helper;
    private String name;
    private int type;

    public UsersInfoPresenter(int type) {
        this.type = type;
        usersInfoRepoAsync = new UsersInfoRepoAsync(type,this);
        helper = new JSONHelper();
    }

    public String getName(){
        return name;
    }

    @Override
    public void loadNameOfUserById(int id) {
        usersInfoRepoAsync = new UsersInfoRepoAsync(type,this);
            usersInfoRepoAsync.execute(id);
    }

    @Override
    public void onUserNameNetworkSuccess(JSONObject vkApiUserFullName) {
        name = helper.getNameFromJson(vkApiUserFullName);
    }

    @Override
    public void onUserNameNetworkFailure() {
        
    }

}
