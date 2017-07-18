package com.client.vk.roma.vkclient.dialogs.repo;

import android.os.AsyncTask;

import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONObject;

/**
 * Created by Roma on 13.06.2017.
 */

public class DialogRepoAsync extends AsyncTask<Integer, Void, Void> {

    private JSONObject jsonMsgArray;
    private OnDialogRepoFinishedListener listener;
    private final int GET_DIALOG_HISTORY_TYPE = 0;
    private final int MARK_AS_READ_TYPE = 1;

    private int type;

    public DialogRepoAsync(int type, OnDialogRepoFinishedListener listener) {
        this.listener = listener;
        this.type = type;
    }


    private void getDialogHistory(int id,int startid) {

        VKRequest request = new VKRequest("messages.getHistory", VKParameters.from(VKApiConst.USER_ID, id, VKApiConst.COUNT, 50,VKApiConst.START_MESSAGE_ID,startid,VKApiConst.REV,1));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                jsonMsgArray = response.json;

                listener.onDialogNetworkSuccess(jsonMsgArray);
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                listener.onDialogNetworkFailure();
            }
        });

    }

    private void markAsRead(int userid,int startmessageid){

        VKRequest vkRequest = new VKRequest("messages.markAsRead",VKParameters.from(VKApiConst.USER_ID,userid,VKApiConst.START_MESSAGE_ID,startmessageid));
        vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

            }
        });

    }


    @Override
    protected Void doInBackground(Integer... params) {

        switch (type) {
            case GET_DIALOG_HISTORY_TYPE:
                getDialogHistory(params[0],params[1]);
                break;
            case MARK_AS_READ_TYPE:
                markAsRead(params[0],params[1]);
                break;
        }
        return null;
    }
}
