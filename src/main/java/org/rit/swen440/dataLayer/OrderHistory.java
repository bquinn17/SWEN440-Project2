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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="UserUser_id")
    private int userId;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="ProductSkuCode")
    private int skuCode;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date dateCreated = new Date();

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "action")
    private int action;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(int skuCode) {
        this.skuCode = skuCode;
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



