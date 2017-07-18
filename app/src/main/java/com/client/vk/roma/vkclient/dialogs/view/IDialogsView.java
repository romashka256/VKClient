package com.client.vk.roma.vkclient.dialogs.view;

import com.client.vk.roma.vkclient.dialogs.models.Dialog;
import com.vk.sdk.api.VKError;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by Roma on 22.05.2017.
 */

public interface IDialogsView {

    void onDialogsLoadedSuccess(List<Dialog> dialog, int more);
    void onDialogsLoadFailure(VKError error);
    void onDialogsLoading();
    void onItemClicked(Dialog dialog);
    String dialogTimeCount(DateTime dateTime);

}
