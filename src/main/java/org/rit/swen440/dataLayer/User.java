package org.rit.swen440.dataLayer;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name="username", length = 64)
    private String userName;

    @Column(name="fullName")
    private String fullName;

    @Column(name="password")
    private String password;

    @OneToMany(targetEntity = WishList.class, mappedBy = "user", fetch = FetchType.EAGER)
    private List<WishList> wishListItems = null;

    @OneToMany(targetEntity = OrderHistory.class, mappedBy = "user", fetch = FetchType.EAGER)
    private List<OrderHistory> orderHistoryItems = null;

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User Details?= Id: " + this.id + ", Name: " + this.fullName + ", Username: " + this.userName;
    }
}
