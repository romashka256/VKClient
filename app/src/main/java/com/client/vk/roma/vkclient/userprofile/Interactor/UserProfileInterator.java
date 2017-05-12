package com.client.vk.roma.vkclient.userprofile.Interactor;

import android.util.Log;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUserFull;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Roma on 11.05.2017.
 */

public class UserProfileInterator {

    private final String FIELDS = "first_name,last_name,bdate,city,universities,status,followers_count";
    private OnUserProfileInteractorFinishedListener listener;

    public UserProfileInterator(OnUserProfileInteractorFinishedListener listener) {
        this.listener = listener;
    }

    public void getProfileInfo() {
        VKRequest requestProfileInfo = VKApi.users().get(VKParameters.from(VKApiConst.FIELDS,FIELDS));
        requestProfileInfo.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                VKApiUserFull vkApiUserFull = null;
                try {
                    JSONObject jsonObject = response.json.getJSONObject("response");
                    vkApiUserFull = new VKApiUserFull(jsonObject);
                    Log.i("tag", vkApiUserFull.first_name);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("tager", e.getMessage());
                }
                listener.onNetworkSuccess(vkApiUserFull);
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                listener.onNetworkFailure(error);
            }

            @Override
            public void onProgress(VKRequest.VKProgressType progressType, long bytesLoaded, long bytesTotal) {
                super.onProgress(progressType, bytesLoaded, bytesTotal);
            }
        });
    }


}
