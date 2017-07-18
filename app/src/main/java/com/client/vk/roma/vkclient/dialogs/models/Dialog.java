package com.client.vk.roma.vkclient.dialogs.models;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

/**
 * Created by Roma on 22.05.2017.
 */

public class Dialog implements Comparable<Dialog>{

    private String imgUrl;
    private String title;
    private String name_of_user;
    private Message message;
    private int userid;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName_of_user() {
        return name_of_user;
    }

    public void setName_of_user(String name_of_user) {
        this.name_of_user = name_of_user;
    }

    public Message getBody() {
        return message;
    }

    public void setBody(Message message) {
        this.message = message;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int compareTo(@NonNull Dialog o) {
        return Long.compare(this.getMessage().getTime(),o.getMessage().getTime());
    }
}
