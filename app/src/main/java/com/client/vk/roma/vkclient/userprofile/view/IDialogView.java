package com.client.vk.roma.vkclient.userprofile.view;

import com.client.vk.roma.vkclient.Message;

import java.util.List;

/**
 * Created by Roma on 13.06.2017.
 */

public interface IDialogView {
    void onDialogLoadedSuccess(List<Message> dialog);
    void onDialogLoadFailure();
    void onDialogLoading();
}
