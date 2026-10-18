package foodfusion.model;

public class Order {
    private final int orderId;
    private final String customerName;
    private final FoodItem foodItem;
    private final int quantity;
    private String status;

    public Order(int orderId, String customerName, FoodItem foodItem, int quantity) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.status = "Order received";
    }

    public int getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public FoodItem getFoodItem() { return foodItem; }
    public int getQuantity() { return quantity; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getTotal() { return foodItem.getPrice() * quantity; }

    public String toCsv() {
        return orderId + "," + customerName.replace(",", " ") + "," +
               foodItem.getName().replace(",", " ") + "," + quantity + "," +
               String.format(java.util.Locale.US, "%.2f", getTotal()) + "," + status.replace(",", " ");
    }

    @Override
    public String toString() {
        return String.format("Order #%d | Customer: %s | %s x %d | Total: Rs. %.2f | Status: %s",
                orderId, customerName, foodItem.getName(), quantity, getTotal(), status);
    }
}
