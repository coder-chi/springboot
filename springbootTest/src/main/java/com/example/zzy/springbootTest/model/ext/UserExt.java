package com.example.zzy.springbootTest.model.ext;

import com.example.zzy.springbootTest.model.User;

/**
 * Created by zhangzhiyuan on 2017/8/23.
 */
public class UserExt extends User {
    private String ticket;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
