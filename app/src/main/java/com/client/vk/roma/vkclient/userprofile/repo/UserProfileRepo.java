package com.client.vk.roma.vkclient.userprofile.repo;

import com.client.vk.roma.vkclient.User;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;

import org.json.JSONObject;

/**
 * Created by Roma on 11.05.2017.
 */

public class UserProfileRepo {

    private final String FIELDS = "first_name,last_name,bdate,city,universities,status,followers_count,home_town,photo_200,online";
    private OnUserProfileRepoFinishedListener listener;
    private VKApiUser vkApiUserList;
    private JSONObject friendsObject;
    private JSONObject photosArray;
    private String count;
    private User user;


    public UserProfileRepo(OnUserProfileRepoFinishedListener listener) {
        this.listener = listener;
    }

    public void getProfileInfo() {

        VKRequest requestPhotosList = new VKRequest("photos.getAll", VKParameters.from(VKApiConst.COUNT, 5, VKApiConst.PHOTO_SIZES, 1));
        requestPhotosList.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                photosArray = response.json;

            }
        });

        final VKRequest requestFriendsCount = VKApi.friends().get(VKParameters.from(VKApiConst.COUNT));
        requestFriendsCount.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                friendsObject = response.json;

            }
        });


        VKRequest requestProfileInfo = VKApi.users().get(VKParameters.from(VKApiConst.FIELDS, FIELDS));
        requestProfileInfo.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                vkApiUserList = ((VKList<VKApiUser>) response.parsedModel).get(0);

                listener.onUserProfileNetworkSuccess(vkApiUserList, friendsObject, photosArray);
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                listener.onUserProfileNetworkFailure(error);
            }

            @Override
            public void onProgress(VKRequest.VKProgressType progressType, long bytesLoaded, long bytesTotal) {
                super.onProgress(progressType, bytesLoaded, bytesTotal);
                listener.onUserProfileNetworkLoading();
            }
        });
    }
}
