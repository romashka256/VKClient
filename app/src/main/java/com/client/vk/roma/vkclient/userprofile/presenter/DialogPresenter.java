package com.client.vk.roma.vkclient.userprofile.presenter;

import com.client.vk.roma.vkclient.userprofile.repo.DialogRepo;
import com.client.vk.roma.vkclient.userprofile.repo.OnDialogsRepoFinishedListener;
import com.client.vk.roma.vkclient.userprofile.repo.OnRequstsForUsersInfoRepoListener;
import com.client.vk.roma.vkclient.userprofile.repo.RequestsForUsersInfoRepo;
import com.client.vk.roma.vkclient.userprofile.JSONHelper;
import com.client.vk.roma.vkclient.userprofile.view.IDialogsView;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKApiDialog;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;

/**
 * Created by Roma on 22.05.2017.
 */

public class DialogPresenter  implements  IDialogsPresenter, OnDialogsRepoFinishedListener,OnRequstsForUsersInfoRepoListener {

    private IDialogsView view;
    private JSONHelper jsonHelper;
    private DialogRepo dialogRepo;
    private RequestsForUsersInfoRepo usersInfoRepo;

    public DialogPresenter(IDialogsView view) {
        this.view = view;
        this.dialogRepo = new DialogRepo(this);
        this.usersInfoRepo = new RequestsForUsersInfoRepo(this);

        jsonHelper = new JSONHelper();
    }

    @Override
    public void loadDialogs() {
        dialogRepo.getDialogs();
    }

    @Override
    public void onDialogsNetworkSuccess(VKList<VKApiDialog> vkDialogResponse) {
        view.onDialogsLoadedSuccess(jsonHelper.getDialogs(vkDialogResponse,usersInfoRepo));
    }

    @Override
    public void onDialogsNetworkLoading() {

    }

    @Override
    public void oDialogsNetworkFailure(VKError error) {

    }

    @Override
    public String onUserNameNetworkSuccess(VKApiUser UserfullName) {
        return UserfullName.first_name + " " + UserfullName.last_name;
    }

    @Override
    public String onUserNameNetworkFailure() {
        return null;
    }
}
