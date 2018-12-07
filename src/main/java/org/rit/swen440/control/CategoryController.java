package org.rit.swen440.control;

import org.rit.swen440.dataLayer.Category;
import org.rit.swen440.repository.CategoryRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class CategoryController {



    /**
     * Get a list of all category names
     *
     * @return list of categories
     */
    public static List<Category> getCategories() {

        return CategoryRepository.getAllRecords();
    }

    /**
     * Get the description of the named category
     *
     * @param category name
     * @return description
     */
    public static String getCategoryDescription(String category) {
        Optional<Category> match = categories.stream().filter(c -> c.getName().equalsIgnoreCase(category)).findFirst();
        return match.map(Category::getDescription).orElse(null);
    }

    /**
     * Get the category that matches the provided category name
     *
     * @param name
     * @return Category, if present
     */
    public static Optional<Category> findCategory(String name) {
        return categories.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    /**
     * Loop through all our categories and write any product records that
     * have been updated.
     */
    public static void writeCategories() {
        for (Category category : categories) {
            writeProducts(category.getProducts());
        }
    }

    /**
     * Get the category object for this directory
     *
     * @param path directory
     * @return Category object, if .cat file exists
     */
    private static Optional<Category> getCategory(Path path) {
        DirectoryStream.Filter<Path> catFilter = new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path path) throws IOException {
                return path.toString().toLowerCase().endsWith("cat");
            }
        };

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, catFilter)) {
            for (Path file : stream) {
                // read the file
                BufferedReader reader = Files.newBufferedReader(file, Charset.forName("US-ASCII"));
                Category category = new Category();

                category.setName(reader.readLine());
                category.setDescription(reader.readLine());
                category.setProducts(loadProducts(path));

                return Optional.of(category);
            }
        } catch (IOException | DirectoryIteratorException e) {
            System.err.println(e);
        }

        return Optional.empty();
    }

}
