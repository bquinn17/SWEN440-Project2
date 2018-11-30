package org.rit.swen440.dataLayer;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;

import java.io.Serializable;

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

    @Column(name = "item_count")
    private int itemCount;

    @Column(name = "reorder_threshold")
    private int reorderThreshold;

    @Column(name = "reorder_amount")
    private int reorderAmount;

    @Column(name = "title", length = 64)
    private String title;

    @Column(name = "description", columnDefinition = "NVARCHAR(1024)")
    private String description;

    @Column(name= "cost")
    private BigDecimal cost;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="categoryId")
    private Category category = null;

    @Setter(AccessLevel.PRIVATE)
    private boolean updated = false;

    public int getSkuCode() {
        return skuCode;
    }

    public int getItemCount() {
        return itemCount;
    }

    public int getThreshold() {
        return reorderThreshold;
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
        this.reorderThreshold = threshold;
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

}
