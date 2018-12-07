package org.rit.swen440.control;

import org.rit.swen440.dataLayer.Category;
import org.rit.swen440.dataLayer.Product;
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

    private Object getProduct(String product) {
        List<Product> products = ProductRepository.getAllRecords();
        for (Object aP : products) {
            if (aP.equals(product)) {
                return aP;
            }
        }
        return null;
    }

    /**
     * Loop through the set of products and write out any updated products
     *
     * @param products set of products
     */
    private void writeProducts(List<Product> products) {
        for (Product product : products) {
            updateProduct(product);
        }
    }

    /**
     * Write an updated product
     *
     * @param product the product
     */
    private void updateProduct(Product product) {
        ProductRepository.updateRecord(product);
    }

}
