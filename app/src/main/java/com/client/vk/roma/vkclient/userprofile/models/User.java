package com.client.vk.roma.vkclient.userprofile.models;

/**
 * Created by Roma on 10.05.2017.
 */

public class User {
    int id;
    static String photo_string;
    static String first_name;
    static String last_name;
    static String followers_count;
    static int friends_count;
    static String bdate;
    static String university;
    static String status;
    static  String[] imgUrls;
    static int is_online;

    public static String getStatus() {
        return status;
    }

    public static String[] getImgUrls() {
        return imgUrls;
    }

    public static void setImgUrls(String[] imgUrls) {
        User.imgUrls = imgUrls;
    }

    public static void setStatus(String status) {
        User.status = status;
    }

    public static int getIs_online() {
        return is_online;
    }

    public static void setIs_online(int is_online) {
        User.is_online = is_online;
    }

    public static String getPhoto_string() {
        return photo_string;
    }

    public static void setPhoto_string(String photo_string) {
        User.photo_string = photo_string;
    }

    public static int getFriends_count() {
        return friends_count;
    }

    public static void setFriends_count(int friends_count) {
        User.friends_count = friends_count;
    }

    public static String getUniversity() {
        return university;
    }

    public static void setUniversity(String university) {
        User.university = university;
    }

    public static String getBdate() {
        return bdate;
    }

    public static void setBdate(String bdate) {
        User.bdate = bdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(String followers_count) {
        this.followers_count = followers_count;
    }
}
