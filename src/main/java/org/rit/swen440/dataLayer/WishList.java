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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="UserUser_id")
    private int userId;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="ProductSkuCode")
    private int skuCode;

}
