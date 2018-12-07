package org.rit.swen440.control;

import org.rit.swen440.dataLayer.Category;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CategoryController {

    private Set<Category> categories = new HashSet<>();

    /**
     * Load the Category information
     *
     * @param directory root directory
     */
    private void loadCategories(String directory) {
        this.dirPath = Paths.get(directory);

        DirectoryStream.Filter<Path> dirFilter = new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path path) throws IOException {
                return Files.isDirectory(path);
            }
        };

        // We're just interested in directories, filter out all other files
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath, dirFilter)) {
            for (Path file : stream) {
                // get the category information from each directory
                Optional<Category> entry = getCategory(file);
                entry.ifPresent(categories::add);
            }
        } catch (IOException | DirectoryIteratorException e) {
            // TODO:  Replace with logger
            System.err.println(e);
        }
    }

    /**
     * Get a list of all category names
     *
     * @return list of categories
     */
    public List<String> getCategories() {

        return categories.stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }

    /**
     * Get the description of the named category
     *
     * @param category name
     * @return description
     */
    public String getCategoryDescription(String category) {
        Optional<Category> match = categories.stream().filter(c -> c.getName().equalsIgnoreCase(category)).findFirst();
        return match.map(Category::getDescription).orElse(null);
    }

    /**
     * Get the category that matches the provided category name
     *
     * @param name
     * @return Category, if present
     */
    public Optional<Category> findCategory(String name) {
        return categories.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    /**
     * Loop through all our categories and write any product records that
     * have been updated.
     */
    public void writeCategories() {
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
    private Optional<Category> getCategory(Path path) {
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
