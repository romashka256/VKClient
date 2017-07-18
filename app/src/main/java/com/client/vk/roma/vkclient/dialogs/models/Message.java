package com.client.vk.roma.vkclient.dialogs.models;

/**
 * Created by Roma on 13.06.2017.
 */

public class Message {
    private String body;
    private long time;
    private int readed;
    private int out;
    private int from_id;
    private int message_id;

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public int getId() {
        return from_id;
    }

    public void setId(int from_id) {
        this.from_id = from_id;
    }

    public String getBody() {
        return body;
    }


    public void setBody(String body) {
        this.body = body;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int isReaded() {
        return readed;
    }

    public void setReaded(int readed) {
        this.readed = readed;
    }

    public int getReaded() {
        return readed;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }
}
