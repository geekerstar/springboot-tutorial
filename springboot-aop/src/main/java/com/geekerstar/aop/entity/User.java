package com.geekerstar.aop.entity;

import javax.xml.crypto.Data;

/**
 * @author geekerstar
 * date: 2019-08-16 17:04
 * description:
 */
public class User {
    private String username;
    private String password;
    private Data createTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Data getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Data createTime) {
        this.createTime = createTime;
    }
}
