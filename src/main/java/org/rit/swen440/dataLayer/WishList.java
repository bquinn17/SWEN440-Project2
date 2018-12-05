package org.rit.swen440.dataLayer;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.*;


/**
 * A record of each product type
 */
@Data
@Entity
@Table(name = "wishList")
public class WishList implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_user_id")
    private User user;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="product_skuCode")
    private Product product;

    public User getUserId() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSkuCode(Product product) {
        this.product = product;
    }

}
