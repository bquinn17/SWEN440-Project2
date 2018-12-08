package org.rit.swen440.dataLayerTests;

import org.junit.*;
import org.rit.swen440.control.CategoryController;
import org.rit.swen440.control.ProductController;
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

    private static CategoryController categoryController;
    private static ProductController productController;

    @BeforeClass
    public static void initializeControllers() {
        ProductRepository.deleteAllRecords();
        CategoryRepository.deleteAllRecords();
        categoryController = new CategoryController();
        productController = new ProductController();
    }

    @Test
    public void testCategoryController() {
        CategoryController categoryController = new CategoryController();
        Category toysCategory = categoryController.findCategory("Toys");
        Assert.assertNotNull(toysCategory);

        Category tracksCategory = categoryController.findCategory("Eight Track Tapes");
        Assert.assertNotNull(tracksCategory);
    }

    @Test
    public void testCategoryCreation() {
        Category category = new Category();
        category.setDescription("Many different print books");
        category.setName("Books");

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

        ProductRepository.createRecord(product);

        Assert.assertNotNull(category);
        Assert.assertNotNull(product);
        Assert.assertEquals(product.getCategory(), category);
    }

    @AfterClass
    public static void cleanUpRepo() {
        ProductRepository.deleteAllRecords();
        CategoryRepository.deleteAllRecords();
    }
}
