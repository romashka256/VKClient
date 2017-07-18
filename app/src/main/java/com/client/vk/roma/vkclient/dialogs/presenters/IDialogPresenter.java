package com.client.vk.roma.vkclient.dialogs.presenters;

import com.client.vk.roma.vkclient.dialogs.models.Message;

import java.util.List;

/**
 * Created by Roma on 13.06.2017.
 */

public interface IDialogPresenter {
    void loadMoreDialog(int id,List<Message> list);
    void markAsRead(int userid,int startmessageid);
}
