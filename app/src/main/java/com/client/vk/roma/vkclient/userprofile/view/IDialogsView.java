package com.client.vk.roma.vkclient.userprofile.view;

import com.client.vk.roma.vkclient.Dialog;
import com.vk.sdk.api.VKError;

import java.util.List;

/**
 * Created by Roma on 22.05.2017.
 */

public interface IDialogsView {

    void onDialogsLoadedSuccess(List<Dialog> dialog);
    void onDialogLoadFailure(VKError error);
    void onDialogLoading();
}
