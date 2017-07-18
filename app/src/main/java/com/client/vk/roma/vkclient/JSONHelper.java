package com.client.vk.roma.vkclient;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.client.vk.roma.vkclient.dialogs.models.Dialog;
import com.client.vk.roma.vkclient.dialogs.models.Message;
import com.client.vk.roma.vkclient.userprofile.models.Friend;
import com.client.vk.roma.vkclient.userprofile.models.User;
import com.vk.sdk.api.model.VKApiDialog;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Roma on 14.05.2017.
 */

public class JSONHelper {
    List<Message> lstMsg = new ArrayList<>();
    User user = new User();
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

    public Map<Integer,Dialog> getDialogs(VKList<VKApiDialog> jsonDialogs) {
        Map<Integer,Dialog> dialogs = new HashMap<>();
        List<Integer> keys = new ArrayList<>();
        List<Dialog> values = new ArrayList<>();

        for (int i = 0; i < jsonDialogs.size(); i++) {
            Message message = new Message();
            VKApiDialog dialogItem = jsonDialogs.get(i);
            Dialog dialog = new Dialog();
            message.setBody(dialogItem.message.body);
            message.setMessage_id(dialogItem.message.id);
            message.setId(dialogItem.message.user_id);
            message.setTime(dialogItem.message.date);
            if(dialogItem.message.read_state){
                message.setReaded(1);
            }else{
                message.setReaded(0);
            }

            dialog.setMessage(message);
            dialog.setUserid(dialogItem.message.user_id);
            dialogs.put(dialogItem.message.user_id,dialog);

            if (!dialogItem.message.title.equals(" ... ")) {
                dialog.setTitle(dialogItem.message.title);
            }

        }

        return sortByValue(dialogs);
    }

    public List<Friend> getFriends(VKList vkFriendsResponse) {

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

    public User getNameFromJson(JSONObject name) {
        User user = new User();
        try {
            JSONObject objName = name.getJSONArray("response").getJSONObject(0);
            user.setFirst_name(objName.getString("first_name"));
            user.setLast_name(objName.getString("last_name"));
            user.setId(objName.getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<Message> getDialogFromJson(JSONObject jsonArray){

        try {
            JSONArray jsonMsgArray = jsonArray.getJSONObject("response").getJSONArray("items");

          //  int count = jsonArray.getJSONObject("response").getInt("count");

            for(int i = 0; i < 50; i++){
                Message msg = new Message();
                msg.setBody(jsonMsgArray.getJSONObject(i).getString("body"));
                msg.setMessage_id(jsonMsgArray.getJSONObject(i).getInt("id"));
                msg.setId(jsonMsgArray.getJSONObject(i).getInt("from_id"));
                msg.setTime(jsonMsgArray.getJSONObject(i).getInt("date"));
                msg.setReaded(jsonMsgArray.getJSONObject(i).getInt("read_state"));
                msg.setOut(jsonMsgArray.getJSONObject(i).getInt("out"));
                lstMsg.add(msg);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }



        return lstMsg;
    }

    private static Map<Integer, Dialog> sortByValue(Map<Integer, Dialog> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<Integer, Dialog>> list =
                new LinkedList<>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<Integer, Dialog>>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public int compare(Map.Entry<Integer, Dialog> o1,
                               Map.Entry<Integer, Dialog> o2) {
                return (int) (o2.getValue().getMessage().getTime() - o1.getValue().getMessage().getTime());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<Integer, Dialog> sortedMap = new LinkedHashMap<Integer, Dialog>();
        for (Map.Entry<Integer, Dialog> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        /*
        //classic iterator example
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }*/

        return sortedMap;
    }
}
