package com.client.vk.roma.vkclient.userprofile.repo;

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
    private int type;

    public DialogRepoAsync(int type,OnDialogRepoFinishedListener listener) {
        this.listener = listener;
        this.type = type;
    }

    private void getDialogHistory(int id) {

        VKRequest request = new VKRequest("messages.getHistory", VKParameters.from(VKApiConst.USER_ID, id, VKApiConst.COUNT, 50));
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


    @Override
    protected Void doInBackground(Integer... params) {

        switch (type){
            case GET_DIALOG_HISTORY_TYPE :
                getDialogHistory(params[0]);
                break;
        }
        return null;
    }
}
