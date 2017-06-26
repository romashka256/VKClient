package com.client.vk.roma.vkclient;

import java.util.Date;

/**
 * Created by Roma on 23.05.2017.
 */

public class Friend {
    int id;
    String last_name;
    String first_name;
    String imgUrs;
    Date last_seen;
    int isonline;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getImgUrs() {
        return imgUrs;
    }

    public void setImgUrs(String imgUrs) {
        this.imgUrs = imgUrs;
    }

    public Date getLast_seen() {
        return last_seen;
    }

    public void setLast_seen(Date last_seen) {
        this.last_seen = last_seen;
    }

    public int getIsonline() {
        return isonline;
    }

    public void setIsonline(int isonline) {
        this.isonline = isonline;
    }
}
