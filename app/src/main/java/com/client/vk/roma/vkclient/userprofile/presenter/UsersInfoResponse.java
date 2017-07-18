package com.client.vk.roma.vkclient.userprofile.presenter;


import com.client.vk.roma.vkclient.JSONHelper;
import com.client.vk.roma.vkclient.dialogs.models.Dialog;
import com.client.vk.roma.vkclient.userprofile.models.User;
import com.client.vk.roma.vkclient.userprofile.repo.UsersInfoRepoAsync;
import com.client.vk.roma.vkclient.userprofile.repo.listeners.OnUsersInfoRepoFinishedListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Roma on 24.05.2017.
 */

public class UsersInfoResponse implements OnUsersInfoRepoFinishedListener {

    private UsersInfoRepoAsync usersInfoRepoAsync;
    private JSONHelper helper;
    private User user;
    private int type;
    private final int GET_USER_NAME_TYPE = 0;
    private Map<Integer, Dialog> dialogs;
    private int i;


    public UsersInfoResponse(int type) {
        this.type = type;
        usersInfoRepoAsync = new UsersInfoRepoAsync(type, this);
        helper = new JSONHelper();
        i = 0;
    }

    public User getUser(int type) {
        switch (type) {
            case GET_USER_NAME_TYPE:
                return user;
        }
        return null;
    }

    public void loadNameOfUserById(int id) {
        usersInfoRepoAsync = new UsersInfoRepoAsync(type, this);
        usersInfoRepoAsync.execute(id);
    }

    @Override
    public void onUserNameNetworkSuccess(JSONObject vkApiUserFullName) {
        user = helper.getNameFromJson(vkApiUserFullName);
            List<Dialog> dialogList = new ArrayList<>();
            dialogList.add(setName(user));
        i++;
        if(i == 14){

        }
    }



    @Override
    public void onUserNameNetworkFailure() {

    }

    public void setNames(Map<Integer, Dialog> dialogs){
        this.dialogs = dialogs;
    }

    public Dialog setName(User user) {

        Dialog dialog;

        dialog = dialogs.get(user.getId());

        dialog.setName_of_user(user.getFirst_name() + " " + user.getLast_name());

        return dialog;
    }
}
