package com.bstq.Service;

/**
 * Created by Jose on 17/05/2017.
 */

public class User {
    int id;
    String userName;
    String email;
    String password;
    int maxScore;
    public User() {
    }

    public User(int id, String userName, String email, String password, int maxScore) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.maxScore = maxScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", maxScore=" + maxScore +
                '}';
    }
}
