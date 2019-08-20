package com.geekerstar.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author geekerstar
 * date: 2019-08-17 09:18
 * description:
 */
public class User implements Serializable {
    private String username;
    private String password;
    private Date createTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
