package com.client.vk.roma.vkclient.dialogs.view;

import com.client.vk.roma.vkclient.dialogs.models.Message;

import java.util.List;

/**
 * Created by Roma on 13.06.2017.
 */

public interface IDialogView {
    void onDialogLoadedSuccess(List<Message> messageList, boolean loadMore);
    void onDialogLoadFailure();
    void onDialogLoading();
}
