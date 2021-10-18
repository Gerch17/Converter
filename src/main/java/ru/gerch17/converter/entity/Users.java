package ru.gerch17.converter.entity;


import javax.persistence.*;

@Entity
@Table(name="users")
public class Users {
    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="user_name", nullable = false)
    private String userName;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name = "user_role", nullable = false)
    private String user_role;

    //@Column(name = "enabled", nullable = false)
    //private boolean enabled;

    public Users(){}

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }
/*
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

 */
}
