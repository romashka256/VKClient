package com.client.vk.roma.vkclient.dialogs.repo;

import android.os.AsyncTask;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiDialog;
import com.vk.sdk.api.model.VKApiGetDialogResponse;
import com.vk.sdk.api.model.VKList;

/**
 * Created by Roma on 22.05.2017.
 */

public class DialogsRepoAsync extends AsyncTask<Integer,Void,Void>{

    private OnDialogsRepoFinishedListener listener;
    private VKList<VKApiDialog> vkApiDialogs;
    private int type;
    private final int GET_DIALOGS_TYPE = 0;




    public DialogsRepoAsync(int type, OnDialogsRepoFinishedListener listener) {
        this.listener = listener;
        this.type = type;
    }

    public void getDialogs(int startid, final int more){

        final VKRequest request = VKApi.messages().getDialogs(VKParameters.from(VKApiConst.COUNT,25,VKApiConst.START_MESSAGE_ID,startid));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                VKApiGetDialogResponse getDialogResponse = (VKApiGetDialogResponse) response.parsedModel;
                vkApiDialogs =  getDialogResponse.items;

                listener.onDialogsNetworkSuccess(vkApiDialogs, more);
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
            }

            @Override
            public void onProgress(VKRequest.VKProgressType progressType, long bytesLoaded, long bytesTotal) {
                super.onProgress(progressType, bytesLoaded, bytesTotal);
            }
        });
    }

    @Override
    protected Void doInBackground(Integer... params) {
        switch (type){
            case GET_DIALOGS_TYPE :
                getDialogs(params[0], params[1]);
                break;
        }
        return null;
    }
}
