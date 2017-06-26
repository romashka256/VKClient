package com.client.vk.roma.vkclient;

import java.util.Date;

/**
 * Created by Roma on 13.06.2017.
 */

public class Message {
    private String body;
    private Date time;
    private boolean readed;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean isReaded() {
        return readed;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }
}
