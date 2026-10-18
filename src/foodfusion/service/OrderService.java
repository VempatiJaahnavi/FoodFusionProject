package foodfusion.service;

import foodfusion.model.FoodItem;
import foodfusion.model.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderService {
    private final List<Order> orders = new ArrayList<>();
    private int nextOrderId = 1001;

    public Order placeOrder(String customerName, FoodItem foodItem, int quantity) {
        if (customerName == null || customerName.isBlank()) throw new IllegalArgumentException("Customer name is required.");
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than zero.");
        Order order = new Order(nextOrderId++, customerName.trim(), foodItem, quantity);
        orders.add(order);
        return order;
    }

    public List<Order> getAll() { return new ArrayList<>(orders); }

    public Optional<Order> findById(int id) {
        return orders.stream().filter(order -> order.getOrderId() == id).findFirst();
    }

    public double getTotalSales() {
        return orders.stream().mapToDouble(Order::getTotal).sum();
    }
}
