package com.udacity.jwdnd.course1.cloudstorage.models;



public class CredentialForm {
    private String url;
    private String username;
    private String password;
    private Integer credentialid;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCredentialid() { return credentialid; }

    public void setCredentialid(Integer credentialid) { this.credentialid = credentialid; }

}
