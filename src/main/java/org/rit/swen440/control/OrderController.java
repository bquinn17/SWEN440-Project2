package org.rit.swen440.control;

import org.hibernate.criterion.Order;
import org.rit.swen440.dataLayer.OrderHistory;
import org.rit.swen440.dataLayer.Product;
import org.rit.swen440.repository.OrderHistoryRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderController {
    /**
     * Place an order, decrement the available itemCount
     *
     * @param amount being ordered
     * @return if order was successfully processed
     */
    public boolean order(int amount, Product product) {
        if (canOrder(amount,product)){
            product.setItemCount(product.getItemCount()-amount);
            return true;
        } else {
            return false;
        }
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

    public List<OrderHistory> getAllOrders(){
        List<OrderHistory> orders = OrderHistoryRepository.getAllRecords();
        return orders;
    }

    public List<OrderHistory> getUserOrders(String userName){
        List<OrderHistory> userOrders = new ArrayList<>();
        List<OrderHistory> allOrders = getAllOrders();
        for (OrderHistory order : allOrders){
            if (order.getUser().getUserName().equals(userName)){
                userOrders.add(order);
            }
        }
        return userOrders;
    }

    public void createOrder(OrderHistory order){
        OrderHistoryRepository.createRecord(order);
    }

    /**
     * Loop through the set of products and write out any updated products
     *
     * @param orders set of orders
     */
    public void writeOrder(List<OrderHistory> orders) {
        for (OrderHistory order : orders) {
            updateProduct(order);
        }
    }

    /**
     * Write an updated order
     *
     * @param order the order
     */
    public void updateProduct(OrderHistory order) {
        OrderHistoryRepository.updateRecord(order);
    }
}
