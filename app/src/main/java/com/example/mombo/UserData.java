package com.example.mombo;

public class UserData {
    String id, email, password, nickname, mynum, famnum, hosnum, steps;

    public UserData(String id, String email, String password, String nickname, String mynum, String famnum, String hosnum, String steps) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.mynum = mynum;
        this.famnum = famnum;
        this.hosnum = hosnum;
        this.steps = steps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMynum() {
        return mynum;
    }

    public void setMynum(String mynum) {
        this.mynum = mynum;
    }

    public String getFamnum() {
        return famnum;
    }

    public void setFamnum(String famnum) {
        this.famnum = famnum;
    }

    public String getHosnum() {
        return hosnum;
    }

    public void setHosnum(String hosnum) {
        this.hosnum = hosnum;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
}
