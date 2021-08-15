package com.udacity.jwdnd.course1.cloudstorage.models;

public class Credential {

    private Integer credentialid;
    private String url;
    private String username;
    private String key;
    private String password;
    private String userid;
    //public Credential(){}

    public Credential( String url, String username, String key, String password, String userid, Integer credentialid) {
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userid = userid;
        this.credentialid = credentialid;
    }

    public Integer getCredentialid() {
        return credentialid;
    }

    public void setCredentialid(Integer credentialid) {
        this.credentialid = credentialid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }



    @Override
    public String toString() {
        return String.format("credentialid: %d\nurl: %s\nusername: %s\nkey: %s\npassword: %s\nuserid: %s",
                this.credentialid, this.url, this.username, this.key, this.password, this.userid);
    }


}
