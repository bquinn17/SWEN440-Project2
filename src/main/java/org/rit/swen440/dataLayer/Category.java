package org.rit.swen440.dataLayer;

import lombok.Data;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * A product category supported by the Order System
 */
@Data
@Entity
@Table(name = "user")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    private Set<Product> products = new HashSet<>();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Optional<Product> findProduct(String name) {
        return products.stream()
                .filter(p -> p.getTitle().equalsIgnoreCase(name))
                .findFirst();
    }
}
