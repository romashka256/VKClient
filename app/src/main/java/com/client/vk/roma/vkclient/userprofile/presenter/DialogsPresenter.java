package com.client.vk.roma.vkclient.userprofile.presenter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.client.vk.roma.vkclient.Dialog;
import com.client.vk.roma.vkclient.R;
import com.client.vk.roma.vkclient.userprofile.JSONHelper;
import com.client.vk.roma.vkclient.userprofile.repo.DialogsRepoAsync;
import com.client.vk.roma.vkclient.userprofile.repo.OnDialogsRepoFinishedListener;
import com.client.vk.roma.vkclient.userprofile.view.DialogFragment;
import com.client.vk.roma.vkclient.userprofile.view.IDialogsView;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKApiDialog;
import com.vk.sdk.api.model.VKList;

import java.util.List;

/**
 * Created by Roma on 22.05.2017.
 */

public class DialogsPresenter implements IDialogsPresenter, OnDialogsRepoFinishedListener {

    private IDialogsView view;
    private JSONHelper jsonHelper;
    private DialogsRepoAsync dialogsRepoAsync;
    private UsersInfoPresenter usersInfoPresenter;
    private final int GET_DIALOGS_TYPE = 0;
    private final int GET_USER_NAME_TYPE = 0;
    private final String POSITION_KEY = "position_key";

    public DialogsPresenter(IDialogsView view) {
        this.view = view;
        this.dialogsRepoAsync = new DialogsRepoAsync(GET_DIALOGS_TYPE, this);
        this.usersInfoPresenter = new UsersInfoPresenter(GET_USER_NAME_TYPE);

        jsonHelper = new JSONHelper();
    }

    @Override
    public void loadDialogs() {
        dialogsRepoAsync = new DialogsRepoAsync(GET_DIALOGS_TYPE, this);
        dialogsRepoAsync.execute();
    }

    @Override
    public void onDialogsNetworkSuccess(VKList<VKApiDialog> vkDialogResponse) {
        List<Dialog> dialogs = jsonHelper.getDialogs(vkDialogResponse);
        for (int i = 0; i < dialogs.size(); i++) {
            if (dialogs.get(i).getTitle() == null) {
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
        Log.i("DialogError", error.errorMessage);
    }

    @Override
    public void onItemClicked(FragmentManager fm, Dialog dialog) {
        DialogFragment dialogFragment = DialogFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION_KEY,dialog.getUserid());

        FragmentTransaction fragmentTransaction = fm
                .beginTransaction();
        fragmentTransaction.replace(R.id.containerFragment,dialogFragment);
        fragmentTransaction.commit();
    }
}
