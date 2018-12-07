package org.rit.swen440.control;

import org.rit.swen440.dataLayer.Product;

public class OrderController {
    /**
     * Place an order, decrement the available itemCount
     *
     * @param amount being ordered
     * @return if order was successfully processed
     */
    public boolean order(int amount, Product product) {
        if (canOrder(amount, product)) {
            product.setItemCount(product.getItemCount() - amount);
            updateProduct(product);  // Need to store the updated product information

            // TODO:  add stock management functionality
            return true;
        }

        return false;
    }

    /**
     * Check to see if we have enough of this item for an order
     *
     * @param amount Number of items being ordered
     * @return true if enough stock
     */
    private boolean canOrder(int amount, Product product) {
        return (product.getItemCount() - amount >= 0);
    }
}
