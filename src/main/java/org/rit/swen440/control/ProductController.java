package org.rit.swen440.control;

import org.rit.swen440.dataLayer.Category;
import org.rit.swen440.dataLayer.Product;
import org.rit.swen440.repository.ProductRepository;
import org.rit.swen440.repository.ProductRepository;
import org.rit.swen440.repository.CategoryRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductController {

    public ProductController(){
        if (ProductRepository.getAllRecords().size() == 0) {
            initializeProducts();
        }
    }

    private void initializeProducts() {
        CategoryController categoryController = new CategoryController();

        Product darkSide = new Product();
        darkSide.setItemCount(13);
        darkSide.setReorderAmount(10);
        darkSide.setReorderThreshold(2);
        darkSide.setTitle("Dark Side of the Moon");
        darkSide.setDescription("A classic Pink Floyd album");
        darkSide.setCost(new BigDecimal(8.99));
        darkSide.setCategory(categoryController.findCategory("Eight Track Tapes"));
        ProductRepository.createRecord(darkSide);

        Product theCars = new Product();
        theCars.setItemCount(13);
        theCars.setReorderAmount(10);
        theCars.setReorderThreshold(4);
        theCars.setTitle("The Cars");
        theCars.setDescription("The Cars is the debut album by the American new wave band the Cars. It was released on June 6, 1978 on Elektra Records. The album, which featured the three charting singles \"Just What I Needed,\" \"My Best Friend's Girl,\" and \"Good Times Roll,\" as well as several album-oriented rock radio hits, was a major success for the band, remaining on the charts for 139 weeks. It has been recognized as one of the band's best albums. -- wikipedia");
        theCars.setCost(new BigDecimal(4.95));
        theCars.setCategory(categoryController.findCategory("Eight Track Tapes"));
        ProductRepository.createRecord(theCars);

        Product rubicsCube = new Product();
        rubicsCube.setItemCount(50);
        rubicsCube.setReorderAmount(10);
        rubicsCube.setReorderThreshold(45);
        rubicsCube.setTitle("Rubic's Cube");
        rubicsCube.setDescription("A mindbending toy, turn the sides to move color tiles.  The objective is to arange the cube so that each side is one color.");
        rubicsCube.setCost(new BigDecimal(5.00));
        rubicsCube.setCategory(categoryController.findCategory("Toys"));
        ProductRepository.createRecord(rubicsCube);
    }

    /**
     * Return a list of Products based on the provided category.
     *
     * @param categoryName Name of Category to use
     * @return List of Products in the category
     */
    public List<Product> getProducts(String categoryName) {
        List<Product> productsInCategory = new ArrayList<>();
        List<Product> allProducts = ProductRepository.getAllRecords();
        for (Product product: allProducts) {
            if (product.getCategory().toString().equals(categoryName)) {
                productsInCategory.add(product);
            }
        }
        return productsInCategory;
    }

    public Product getProduct(String product) {
        List<Product> products = ProductRepository.getAllRecords();
        for (Object aP : products) {
            if (aP.equals(product)) {
                return (Product) aP;
            }
        }
        return null;
    }

    /**
     * Loop through the set of products and write out any updated products
     *
     * @param products set of products
     */
    public void writeProducts(List<Product> products) {
        for (Product product : products) {
            updateProduct(product);
        }
    }

    /**
     * Write an updated product
     *
     * @param product the product
     */
    public void updateProduct(Product product) {
        ProductRepository.updateRecord(product);
    }

}
