package org.rit.swen440;

import org.junit.Assert;
import org.junit.Test;
import org.rit.swen440.dataLayer.Category;
import org.rit.swen440.dataLayer.Product;
import org.rit.swen440.repository.CategoryRepository;
import org.rit.swen440.repository.ProductRepository;
import java.util.Random;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Unit test for menutest Application.
 */
public class orderSystemTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        Category category = new Category();
        category.setDescription("Toys");
        category.setName("RC car");

        Product product = new Product();
        product.setCost(new BigDecimal(100));
        product.setDescription("The coolest RC car ever");
        product.setItemCount(10);

        Random rand = new Random();
        product.setSkuCode(rand.nextInt(2000000000));
        product.setTitle("A cool RC car");
        product.setCategory(category);
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);

        category.setProducts(products);

        ProductRepository.createRecord(product);

        Assert.assertNotNull(category);
        Assert.assertNotNull(category.getProducts());
    }
}
