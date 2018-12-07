package org.rit.swen440.dataLayerTests;

import org.junit.Assert;
import org.junit.Test;
import org.rit.swen440.dataLayer.Category;
import org.rit.swen440.dataLayer.Product;
import org.rit.swen440.repository.CategoryRepository;
import org.rit.swen440.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Unit test for menutest Application.
 */
public class orderSystemTest {


    @Test
    public void testCategoryCreation() {
        Category category = new Category();
        category.setDescription("Music");
        category.setName("8 Track Tapes");

        CategoryRepository.createRecord(category);
        Assert.assertNotNull(category.getId());
    }

    @Test
    public void testProductCreation() {
        Category category = new Category();
        category.setDescription("Toys");
        category.setName("RC car");

        Product product = new Product();
        product.setCost(new BigDecimal(100));
        product.setDescription("The coolest RC car ever");
        product.setTitle("A cool RC car");
        product.setItemCount(100);
        product.setReorderAmount(90);
        product.setReorderThreshold(10);
        product.setCategory(category);
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        category.setProducts(products);

        ProductRepository.createRecord(product);

        Assert.assertNotNull(category);
        Assert.assertNotNull(category.getProducts());
    }
}
