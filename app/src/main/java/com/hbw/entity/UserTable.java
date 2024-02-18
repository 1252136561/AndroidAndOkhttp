package com.hbw.entity;


/**
 * <p>
 * 
 * </p>
 *
 * @author hbw
 * @since 2023-03-07
 */
public class UserTable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userName;

    public UserTable(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public UserTable(String userName, String userPassword, int role, String email) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.role = role;
        this.email = email;
    }

    public UserTable(String userName) {
        this.userName = userName;
    }

    public UserTable() {

    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String userPassword;

    private int role;

    private String email;


}
