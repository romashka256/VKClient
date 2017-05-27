package com.client.vk.roma.vkclient.userprofile;

import com.client.vk.roma.vkclient.Dialog;
import com.client.vk.roma.vkclient.User;
import com.client.vk.roma.vkclient.userprofile.repo.RequestsForUsersInfoRepo;
import com.vk.sdk.api.model.VKApiDialog;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roma on 14.05.2017.
 */

public class JSONHelper {
    User user = new User();
    List<Dialog> listOfDialogs;
    RequestsForUsersInfoRepo usersInfoRepo;
    private VKList vkFriendsResponsePriv;
    ArrayList<Friend> friends = new ArrayList<>();

    public User getUserFromJson(VKApiUser userJson, JSONObject jsonFriends, JSONObject jsonPhotos) {

        JSONObject userObj;
        JSONObject object;
        JSONObject photoObj;
        JSONArray photAR;

        String imgUrls[] = null;
        int id = 0;

        try {

            photAR = jsonPhotos.getJSONObject("response").getJSONArray("items");

            imgUrls = new String[photAR.length()];

            for (int i = 0; i < photAR.length(); i++) {
                photoObj = (JSONObject) photAR.get(i);
                imgUrls[i] = photoObj.getJSONArray("sizes").getJSONObject(0).getString("src");
            }

            userObj = userJson.fields;
            object = userObj.getJSONArray("universities").getJSONObject(0);
            user.setUniversity(object.getString("name"));
            user.setStatus(userObj.getString("status"));
            user.setIs_online(userObj.getInt("online"));
            user.setPhoto_string(userObj.getString("photo_200"));
            user.setFollowers_count(userObj.getString("followers_count"));
            user.setFirst_name(userObj.getString("first_name"));
            user.setLast_name(userObj.getString("last_name"));
            user.setBdate(userObj.getString("bdate"));
            user.setFriends_count(jsonFriends.getJSONObject("response").getInt("count"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        user.setImgUrls(imgUrls);
        user.setId(id);

        return user;
    }

    public List<Dialog> getDialogs(VKList<VKApiDialog> jsonDialogs, RequestsForUsersInfoRepo forUsersInfoRepo) {
        ArrayList<Dialog> dialogs = new ArrayList<>();
        listOfDialogs = new ArrayList<>();

        for (int i = 0; i < jsonDialogs.size(); i++) {
            VKApiDialog dialogItem = jsonDialogs.get(i);
            Dialog dialog = new Dialog();
            dialog.setBody(dialogItem.message.body);
            dialog.setUserid(dialogItem.message.user_id);
            dialogs.add(dialog);

            if (!dialogItem.message.title.equals(" ... ")) {
                dialog.setTitle(dialogItem.message.title);
            } else {
                dialog.setName_of_user((forUsersInfoRepo.getNameOfUserById(dialogItem.message.user_id)));
            }
        }
        listOfDialogs.addAll(dialogs);
        return listOfDialogs;
    }

    public List<Friend> getFriends(VKList vkFriendsResponse) {

        vkFriendsResponsePriv = vkFriendsResponse;

        JSONObject jsonFriend;
        try {
            for (int i = 0; i < vkFriendsResponse.size(); i++) {
                Friend friend = new Friend();
                jsonFriend = vkFriendsResponse.get(i).fields;

                friend.setId(jsonFriend.getInt("id"));
                friend.setFirst_name(jsonFriend.getString("first_name"));
                friend.setLast_name(jsonFriend.getString("last_name"));
                friend.setImgUrs(jsonFriend.getString("photo_50"));
                friend.setIsonline(jsonFriend.getInt("online"));

                friends.add(friend);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return friends;
    }
}
