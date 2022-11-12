package com.example.iitbhilai.Model;

public class User {
    private String name,branch,email,assword,coveric,userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public User(String name, String branch, String email, String assword) {
        this.name = name;
        this.branch = branch;
        this.email = email;
        this.assword = assword;
    }


    public User() {
    }

    public String getCoveric() {
        return coveric;
    }

    public void setCoveric(String coveric) {
        this.coveric = coveric;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAssword() {
        return assword;
    }

    public void setAssword(String assword) {
        this.assword = assword;
    }
}
