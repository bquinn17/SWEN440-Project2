package org.rit.swen440.control;

import org.rit.swen440.dataLayer.Category;
import org.rit.swen440.dataLayer.Product;

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
    public List<String> getProducts(String categoryName) {
        Optional<Category> category = findCategory(categoryName);

        return category.map(c -> c.getProducts().stream()
                .map(Product::getTitle)
                .collect(Collectors.toList()))
                .orElse(null);
    }


    public String getProductInformation(String category, String product, Controller.PRODUCT_FIELD field) {
        Optional<Product> selectedProduct = getProduct(category, product);
        switch (field) {
            case NAME:
                return selectedProduct.map(Product::getTitle).orElse(null);

            case DESCRIPTION:
                return selectedProduct.map(Product::getDescription).orElse(null);

            case COST:
                return selectedProduct.map(p -> String.format("%.2f", p.getCost())).orElse(null);

            case INVENTORY:
                return selectedProduct.map(p -> String.valueOf(p.getItemCount())).orElse(null);
        }

        return null;
    }

    private Optional<Product> getProduct(String category, String product) {
        return findCategory(category).map(c -> c.findProduct(product)).orElse(null);
    }

    /**
     * Parse a subdirectory and create a product object for each product within it
     *
     * @param path the subdirectory we're working in
     * @return a set of products
     */
    private List<Product> loadProducts(Path path) {
        DirectoryStream.Filter<Path> productFilter = new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path path) throws IOException {
                return !Files.isDirectory(path) && !path.toString().toLowerCase().endsWith("cat");
            }
        };

        List<Product> products = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, productFilter)) {
            for (Path productFile : stream) {
                // Read the product file
                try (BufferedReader reader = Files.newBufferedReader(productFile, Charset.forName("US-ASCII"))) {
                    Product product = new Product();
                    product.setItemCount(Integer.valueOf(reader.readLine()));
                    product.setReorderThreshold(Integer.valueOf(reader.readLine()));
                    product.setReorderAmount(Integer.valueOf(reader.readLine()));
                    product.setTitle(reader.readLine());
                    product.setDescription(reader.readLine());
                    product.setCost(new BigDecimal(reader.readLine()));

                    products.add(product);
                } catch (Exception e) {
                    // Failed to read a product.  Log the error and continue
                    System.err.println("Failed to read file: " + path.toString());
                }
            }
        } catch (IOException | DirectoryIteratorException e) {
            System.err.println(e);
        }

        return products;
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
//    try (BufferedWriter writer = Files.newBufferedWriter(product.getPath(), Charset.forName("US-ASCII"))){
//      writer.write(String.valueOf(product.getSkuCode()));
//      writer.newLine();
//      writer.write(String.valueOf(product.getItemCount()));
//      writer.newLine();
//      writer.write(String.valueOf(product.getThreshold()));
//      writer.newLine();
//      writer.write(String.valueOf(product.getReorderAmount()));
//      writer.newLine();
//      writer.write(product.getTitle());
//      writer.newLine();
//      writer.write(product.getDescription());
//      writer.newLine();
//      writer.write(product.getCost().toString());
//    } catch(IOException e) {
//      System.err.println("Failed to write product file for:" + product.getTitle());
//    }
    }

}
