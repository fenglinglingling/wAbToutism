package com.abs.pojo;

import java.io.Serializable;

/**
 * (AbUser)实体类
 *
 * @author makejava
 * @since 2022-11-08 18:42:53
 */
public class AbUser implements Serializable {
    private static final long serialVersionUID = -70493439571178369L;
    public static String USERID ="";
    private String userId;
    
    private String userPhone;
    
    private String userName;
    
    private String userPassword;
    
    private String userRole;
    
    private String userGender;
    
    private String userInstruction;
    
    private String userImage;

    @Override
    public String toString() {
        return "AbUser{" +
                "userId='" + userId + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userInstruction='" + userInstruction + '\'' +
                ", userImage='" + userImage + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserInstruction() {
        return userInstruction;
    }

    public void setUserInstruction(String userInstruction) {
        this.userInstruction = userInstruction;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

}

