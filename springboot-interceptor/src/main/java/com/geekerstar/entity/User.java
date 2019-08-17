package com.geekerstar.entity;

import java.io.Serializable;

/**
 * @author geekerstar
 * date: 2019-08-17 10:15
 * description:
 */
public class User implements Serializable {
    private String userName;
    private String passWord;

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
