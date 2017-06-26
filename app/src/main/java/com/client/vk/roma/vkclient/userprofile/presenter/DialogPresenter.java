package com.client.vk.roma.vkclient.userprofile.presenter;

import com.client.vk.roma.vkclient.userprofile.JSONHelper;
import com.client.vk.roma.vkclient.userprofile.repo.DialogRepoAsync;
import com.client.vk.roma.vkclient.userprofile.repo.OnDialogRepoFinishedListener;
import com.client.vk.roma.vkclient.userprofile.view.IDialogView;

import org.json.JSONObject;

/**
 * Created by Roma on 13.06.2017.
 */

public class DialogPresenter implements IDialogPresenter, OnDialogRepoFinishedListener {

    private DialogRepoAsync dialogRepoAsync;
    private IDialogView view;
    private JSONHelper jsonHelper;
    private final int GET_DIALOG_BY_ID_TYPE = 0;

    public DialogPresenter(IDialogView view) {
        this.view = view;
        jsonHelper = new JSONHelper();

        dialogRepoAsync = new DialogRepoAsync(GET_DIALOG_BY_ID_TYPE, this);
    }

    @Override
    public void loadDialog(int id) {
        dialogRepoAsync.execute(id);
    }

    @Override
    public void onDialogNetworkSuccess(JSONObject jsonDialog) {
        view.onDialogLoadedSuccess(jsonHelper.getDialogFromJson(jsonDialog));
    }

    @Override
    public void onDialogNetworkFailure() {
        view.onDialogLoadFailure();
    }
}
