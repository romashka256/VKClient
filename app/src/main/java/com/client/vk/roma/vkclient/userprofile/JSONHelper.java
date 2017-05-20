package com.client.vk.roma.vkclient.userprofile;

import com.client.vk.roma.vkclient.User;
import com.vk.sdk.api.model.VKApiUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Roma on 14.05.2017.
 */

public class JSONHelper {

    public User user = new User();

    public User getUserFromJson(VKApiUser userJson,JSONObject jsonFriends,JSONObject jsonPhotos) {

        JSONObject userObj;
        JSONObject object;
        JSONObject photoObj;
        JSONArray photAR;

        String imgUrls[] = null;
        String photo_200 = null;
        String followers_count = null;
        String first_name = null;
        String last_name = null;
        String status = null;
        String bdate = null;
        String university = null;
        int count_friends = 0;
        int id = 0;
        int is_online = 0;

        try {

            photAR = jsonPhotos.getJSONObject("response").getJSONArray("items");

            imgUrls = new String[photAR.length()];

            for(int i = 0;i<photAR.length();i++){
                photoObj = (JSONObject) photAR.get(i);
                imgUrls[i] = photoObj.getJSONArray("sizes").getJSONObject(0).getString("src");
            }

            userObj = userJson.fields;
            object = userObj.getJSONArray("universities").getJSONObject(0);
            university = object.getString("name");
            status = userObj.getString("status");
            is_online = userObj.getInt("online");
            photo_200 = userObj.getString("photo_200");
            followers_count = userObj.getString("followers_count");
            first_name = userObj.getString("first_name");
            last_name = userObj.getString("last_name");
            bdate = userObj.getString("bdate");
            count_friends = jsonFriends.getJSONObject("response").getInt("count");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        user.setImgUrls(imgUrls);
        user.setStatus(status);
        user.setIs_online(is_online);
        user.setPhoto_string(photo_200);
        user.setUniversity(university);
        user.setFriends_count(count_friends);
        user.setBdate(bdate);
        user.setFollowers_count(followers_count);
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setId(id);

        return user;
    }
}
