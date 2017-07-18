package com.client.vk.roma.vkclient.dialogs.presenters;

import com.client.vk.roma.vkclient.JSONHelper;
import com.client.vk.roma.vkclient.dialogs.models.Message;
import com.client.vk.roma.vkclient.dialogs.repo.DialogRepoAsync;
import com.client.vk.roma.vkclient.dialogs.repo.OnDialogRepoFinishedListener;
import com.client.vk.roma.vkclient.dialogs.view.IDialogView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roma on 13.06.2017.
 */

public class DialogPresenter implements IDialogPresenter, OnDialogRepoFinishedListener {

    private List<Message> messageList;
    private IDialogView view;
    private JSONHelper jsonHelper;
    private final int GET_DIALOG_BY_ID_TYPE = 0;
    private final int MARK_AS_READ_TYPE = 1;

    public DialogPresenter(IDialogView view) {
        this.view = view;
        jsonHelper = new JSONHelper();

    }

    @Override
    public void loadMoreDialog(int id, List<Message> messageList) {

        this.messageList = messageList;
        int lastMessageId;

        if (messageList == null) {
            lastMessageId = -1;
        } else {
            lastMessageId = messageList.get(0).getMessage_id();
        }

        new DialogRepoAsync(GET_DIALOG_BY_ID_TYPE, this).execute(id, lastMessageId);
    }

    @Override
    public void onDialogNetworkSuccess(JSONObject jsonDialog) {
        List<Message> listOfMessages = jsonHelper.getDialogFromJson(jsonDialog);
        boolean loadMore;
        if (messageList == null) {
            loadMore = false;
            messageList = new ArrayList<>();
        } else {
            loadMore = true;
        }

        messageList.addAll(0,listOfMessages);

        view.onDialogLoadedSuccess(messageList, loadMore);
    }

    @Override
    public void onDialogNetworkFailure() {
        view.onDialogLoadFailure();
    }

    @Override
    public void markAsRead(int userid, int startmessageid) {
        new DialogRepoAsync(MARK_AS_READ_TYPE, this).execute(userid, startmessageid);
    }
}
