package com.client.vk.roma.vkclient.userprofile.repo;

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

public class DialogRepo {

    private OnDialogsRepoFinishedListener listener;
    private VKList<VKApiDialog> vkApiDialogs;


    public DialogRepo(OnDialogsRepoFinishedListener listener) {
        this.listener = listener;
    }

    public void getDialogs(){

        final VKRequest request = VKApi.messages().getDialogs(VKParameters.from(VKApiConst.COUNT,15));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                VKApiGetDialogResponse getDialogResponse = (VKApiGetDialogResponse) response.parsedModel;
                vkApiDialogs =  getDialogResponse.items;

                listener.onDialogsNetworkSuccess(vkApiDialogs);
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
}
