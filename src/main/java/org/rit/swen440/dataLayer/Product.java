package org.rit.swen440.dataLayer;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * A record of each product type
 */
@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "skuCode")
    private int skuCode;

    @Column(name = "itemCount")
    private int itemCount;

    @Column(name = "threshold")
    private int threshold;

    @Column(name = "reorderAmount")
    private int reorderAmount;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name= "cost")
    private BigDecimal cost;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="categoryId")
    private Category category = null;

    @OneToMany(targetEntity = WishList.class, mappedBy = "skuCode", fetch = FetchType.EAGER)
    private List<WishList> wishListItems = null;

    @Setter(AccessLevel.PRIVATE)
    private boolean updated = false;

    public int getSkuCode() {
        return skuCode;
    }

    public int getItemCount() {
        return itemCount;
    }

    public int getThreshold() {
        return threshold;
    }

    public int getReorderAmount() {
        return reorderAmount;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public void setReorderAmount(int reorderAmount) {
        this.reorderAmount = reorderAmount;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * Check to see if we have enough of this item for an order
     *
     * @param amount Number of items being ordered
     * @return true if enough stock
     */
    public boolean canOrder(int amount) {
        return (itemCount - amount >= 0);
    }

    /**
     * Place an order, decrement the available itemCount
     *
     * @param amount being ordered
     * @return if order was successfully processed
     */
    public boolean order(int amount) {
        if (canOrder(amount)) {
            itemCount = itemCount - amount;
            setUpdated(true);  // Need to store the updated product information

            // TODO:  add stock management functionality
            return true;
        }

        return false;
    }
}
