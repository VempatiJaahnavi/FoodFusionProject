package foodfusion;

import foodfusion.model.FoodItem;
import foodfusion.model.Order;
import foodfusion.payment.DemoUpiPayment;
import foodfusion.payment.PaymentMethod;
import foodfusion.service.MenuService;
import foodfusion.service.OrderFileService;
import foodfusion.service.OrderService;
import foodfusion.util.InputHelper;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** Main console application for the Food Fusion restaurant project. */
public class FoodFusionApp {
    private final Scanner scanner = new Scanner(System.in);
    private final InputHelper input = new InputHelper(scanner);
    private final MenuService menuService = new MenuService();
    private final OrderService orderService = new OrderService();
    private final OrderFileService fileService = new OrderFileService();

    public static void main(String[] args) {
        new FoodFusionApp().run();
    }

    private void run() {
        boolean running = true;
        System.out.println("================================");
        System.out.println("       WELCOME TO FOOD FUSION");
        System.out.println("================================");
        while (running) {
            showMainMenu();
            int choice = input.readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> showMenu();
                case 2 -> placeOrder();
                case 3 -> showOrders();
                case 4 -> showBillSummary();
                case 5 -> updateOrderStatus();
                case 6 -> searchFood();
                case 7 -> sortMenuByPrice();
                case 8 -> showCollectionsAndStreams();
                case 9 -> saveOrders();
                case 10 -> readSavedOrders();
                case 11 -> matrixAddition();
                case 12 -> recursionExample();
                case 13 -> paymentDemo();
                case 14 -> stringExample();
                case 15 -> operatorsAndLoops();
                case 0 -> { running = false; System.out.println("Thank you for visiting Food Fusion!"); }
                default -> System.out.println("Invalid choice. Please choose from the menu.");
            }
            System.out.println();
        }
    }

    private void showMainMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Display food menu");
        System.out.println("2. Place an order");
        System.out.println("3. View all orders");
        System.out.println("4. Bill and sales summary");
        System.out.println("5. Update order status");
        System.out.println("6. Search food by name");
        System.out.println("7. Sort menu by price");
        System.out.println("8. Collections and Streams report");
        System.out.println("9. Save orders to file");
        System.out.println("10. Read saved orders from file");
        System.out.println("11. Matrix addition example");
        System.out.println("12. Recursion example");
        System.out.println("13. Interface and payment example");
        System.out.println("14. String processing example");
        System.out.println("15. Operators and loops examples");
        System.out.println("0. Exit");
    }

    private void showMenu() {
        System.out.println("\n--- FOOD MENU ---");
        menuService.getAll().forEach(System.out::println);
    }

    private void placeOrder() {
        showMenu();
        String customer = input.readText("Customer name: ");
        int foodId = input.readInt("Enter food ID: ");
        var item = menuService.findById(foodId);
        if (item.isEmpty()) { System.out.println("Food ID not found."); return; }
        int quantity = input.readInt("Quantity: ");
        try {
            Order order = orderService.placeOrder(customer, item.get(), quantity);
            System.out.println("Order placed successfully!");
            System.out.println(order);
            String pay = input.readText("Pay now using demo UPI? (yes/no): ");
            if (pay.equalsIgnoreCase("yes")) {
                PaymentMethod payment = new DemoUpiPayment();
                payment.pay(order.getTotal());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Could not place order: " + e.getMessage());
        }
    }

    private void showOrders() {
        List<Order> orders = orderService.getAll();
        if (orders.isEmpty()) { System.out.println("No orders placed yet."); return; }
        orders.forEach(System.out::println);
    }

    private void showBillSummary() {
        List<Order> orders = orderService.getAll();
        if (orders.isEmpty()) { System.out.println("No orders available for billing."); return; }
        System.out.println("--- BILL SUMMARY ---");
        orders.forEach(order -> System.out.printf("Order #%d: Rs. %.2f%n", order.getOrderId(), order.getTotal()));
        System.out.printf("Total sales: Rs. %.2f%n", orderService.getTotalSales());
    }

    private void updateOrderStatus() {
        int id = input.readInt("Enter order ID: ");
        var found = orderService.findById(id);
        if (found.isEmpty()) { System.out.println("Order not found."); return; }
        System.out.println("1. Preparing\n2. Ready for pickup\n3. Out for delivery\n4. Delivered");
        int choice = input.readInt("Choose new status: ");
        String status = switch (choice) {
            case 1 -> "Preparing";
            case 2 -> "Ready for pickup";
            case 3 -> "Out for delivery";
            case 4 -> "Delivered";
            default -> null;
        };
        if (status == null) { System.out.println("Invalid status choice."); return; }
        found.get().setStatus(status);
        System.out.println("Status updated: " + found.get());
    }

    private void searchFood() {
        String term = input.readText("Enter food name to search: ");
        List<FoodItem> matches = menuService.searchByName(term);
        if (matches.isEmpty()) System.out.println("No matching food found.");
        else matches.forEach(System.out::println);
    }

    private void sortMenuByPrice() {
        System.out.println("--- MENU SORTED BY PRICE ---");
        menuService.sortedByPrice().forEach(System.out::println);
    }

    private void showCollectionsAndStreams() {
        List<FoodItem> menu = menuService.getAll();
        Predicate<FoodItem> underTwoHundred = item -> item.getPrice() < 200;
        System.out.println("Food items under Rs. 200:");
        menu.stream().filter(underTwoHundred).forEach(System.out::println);
        System.out.println("Food names joined: " + menu.stream().map(FoodItem::getName).collect(Collectors.joining(", ")));
        System.out.println("Number of menu items: " + menu.stream().count());
        System.out.printf("Average price: Rs. %.2f%n", menu.stream().mapToDouble(FoodItem::getPrice).average().orElse(0));
    }

    private void saveOrders() {
        try {
            fileService.save(orderService.getAll());
            System.out.println("Orders saved to: " + fileService.getFilePath());
        } catch (IOException e) { System.out.println("Unable to save orders: " + e.getMessage()); }
    }

    private void readSavedOrders() {
        try { fileService.displaySavedOrders(); }
        catch (IOException e) { System.out.println("Unable to read orders: " + e.getMessage()); }
    }

    private void matrixAddition() {
        int[][] first = {{1, 2}, {3, 4}};
        int[][] second = {{5, 6}, {7, 8}};
        System.out.println("Sum of two 2x2 matrices:");
        for (int row = 0; row < first.length; row++) {
            for (int col = 0; col < first[row].length; col++) System.out.print((first[row][col] + second[row][col]) + " ");
            System.out.println();
        }
    }

    private void recursionExample() {
        int n = input.readInt("Enter a number (0-12) for factorial: ");
        if (n < 0 || n > 12) { System.out.println("Please enter a number from 0 to 12."); return; }
        System.out.println(n + "! = " + factorial(n));
    }

    private long factorial(int n) { return n <= 1 ? 1 : n * factorial(n - 1); }

    private void paymentDemo() {
        double amount = 250.00;
        PaymentMethod payment = new DemoUpiPayment();
        payment.pay(amount);
        System.out.println("This is only a classroom demonstration, not a real payment gateway.");
    }

    private void stringExample() {
        String food = input.readText("Enter a food name: ");
        String cleaned = food.trim();
        System.out.println("Uppercase: " + cleaned.toUpperCase());
        System.out.println("Lowercase: " + cleaned.toLowerCase());
        System.out.println("Length: " + cleaned.length());
        System.out.println("Contains 'a': " + cleaned.toLowerCase().contains("a"));
        System.out.println("Reversed using StringBuilder: " + new StringBuilder(cleaned).reverse());
    }

    private void operatorsAndLoops() {
        int a = input.readInt("Enter integer a: ");
        int b = input.readInt("Enter integer b: ");
        System.out.println("a + b = " + (a + b));
        System.out.println("a > b = " + (a > b));
        System.out.println("Both positive = " + (a > 0 && b > 0));
        System.out.println("Larger number = " + (a > b ? a : b));
        System.out.println("Bitwise a & b = " + (a & b));
        System.out.println("Numbers from 1 to 5 using a loop:");
        for (int i = 1; i <= 5; i++) {
            if (i == 3) continue;
            System.out.print(i + " ");
        }
        System.out.println();
        double narrowed = (int) 12.75;
        System.out.println("Narrowing type cast of 12.75 to int: " + (int) narrowed);
    }
}
