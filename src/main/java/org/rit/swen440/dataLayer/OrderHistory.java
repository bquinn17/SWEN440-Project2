package org.rit.swen440.dataLayer;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "orderHistory")
public class OrderHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_user_id")
    private User user;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="product_skuCode")
    private Product product;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date dateCreated = new Date();

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "action")
    private int action;

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
}



