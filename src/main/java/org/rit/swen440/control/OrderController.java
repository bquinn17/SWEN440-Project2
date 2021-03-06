package org.rit.swen440.control;

import org.rit.swen440.dataLayer.OrderHistory;
import org.rit.swen440.dataLayer.Product;
import org.rit.swen440.dataLayer.User;
import org.rit.swen440.repository.OrderHistoryRepository;
import org.rit.swen440.repository.ProductRepository;

import java.sql.Date;
import java.time.LocalDateTime;
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
            ProductRepository.updateRecord(product);
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

    public void returnOrder(OrderHistory order) {
        order.setAction(1);
        OrderHistoryRepository.updateRecord(order);
    }

    public void createOrder(int orderCount, User currentUser, Product product) {

        if (order(orderCount, product) == false) {
            reorder(product, orderCount);
            order(orderCount, product);
        }
        if (product.getItemCount() < product.getThreshold()) {
            reorder(product);
        }

        OrderHistory order = new OrderHistory();
        order.setProduct(product);
        order.setQuantity(orderCount);
        if (orderCount > 0) {
            order.setAction(1);
        }else{
            order.setAction(0);
        }
        order.setUser(currentUser);
        order.setDateCreated(Date.valueOf(LocalDateTime.now().toLocalDate()));
        OrderHistoryRepository.createRecord(order);
    }

    private void reorder(Product product, int orderCount) {
        product.setItemCount(product.getItemCount() + product.getReorderAmount() + orderCount);
        ProductRepository.updateRecord(product);
    }

    private void reorder(Product product) {
        product.setItemCount(product.getItemCount() + product.getReorderAmount());
        ProductRepository.updateRecord(product);
    }
}
