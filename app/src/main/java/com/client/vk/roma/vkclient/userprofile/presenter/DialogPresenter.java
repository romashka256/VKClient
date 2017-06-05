package com.client.vk.roma.vkclient.userprofile.presenter;

import android.util.Log;

import com.client.vk.roma.vkclient.Dialog;
import com.client.vk.roma.vkclient.userprofile.JSONHelper;
import com.client.vk.roma.vkclient.userprofile.repo.DialogRepo;
import com.client.vk.roma.vkclient.userprofile.repo.OnDialogsRepoFinishedListener;
import com.client.vk.roma.vkclient.userprofile.view.IDialogsView;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKApiDialog;
import com.vk.sdk.api.model.VKList;

import java.util.List;

/**
 * Created by Roma on 22.05.2017.
 */

public class DialogPresenter  implements  IDialogsPresenter, OnDialogsRepoFinishedListener {

    private IDialogsView view;
    private JSONHelper jsonHelper;
    private DialogRepo dialogRepo;
    private UsersInfoPresenter usersInfoPresenter;

    public DialogPresenter(IDialogsView view) {
        this.view = view;
        this.dialogRepo = new DialogRepo(this);
        this.usersInfoPresenter = new UsersInfoPresenter();

        jsonHelper = new JSONHelper();
    }

    @Override
    public void loadDialogs() {
        dialogRepo.getDialogs();
    }

    @Override
    public void onDialogsNetworkSuccess(VKList<VKApiDialog> vkDialogResponse) {
        List<Dialog> dialogs = jsonHelper.getDialogs(vkDialogResponse);
        for(int i = 0; i<dialogs.size();i++){
            if(dialogs.get(i).getTitle() == null){
                usersInfoPresenter.loadNameOfUserById(dialogs.get(i).getUserid());
                dialogs.get(i).setName_of_user(usersInfoPresenter.getName());
            }
        }

        view.onDialogsLoadedSuccess(dialogs);
    }

    @Override
    public void onDialogsNetworkLoading() {

    }

    @Override
    public void oDialogsNetworkFailure(VKError error) {

        Log.i("DialogError",error.errorMessage);

    }
}
