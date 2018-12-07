package org.rit.swen440.dataLayer;

import lombok.Data;

import java.util.List;
import java.util.Optional;

import java.io.Serializable;

import javax.persistence.*;


/**
 * A product category supported by the Order System
 */
@Data
@Entity
@Table(name = "category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(targetEntity = Product.class, mappedBy = "category", fetch = FetchType.EAGER)
    private List<Product> products = null;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Optional<Product> findProduct(String name) {
        return products.stream()
                .filter(p -> p.getTitle().equalsIgnoreCase(name))
                .findFirst();
    }
}
