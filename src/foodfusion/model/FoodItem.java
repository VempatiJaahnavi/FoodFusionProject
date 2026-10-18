package foodfusion.model;

public class FoodItem implements Comparable<FoodItem> {
    private final int id;
    private final String name;
    private final double price;
    private final String category;

    public FoodItem(int id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }

    @Override
    public int compareTo(FoodItem other) {
        return Double.compare(this.price, other.price);
    }

    @Override
    public String toString() {
        return String.format("%d. %-12s Rs. %.2f (%s)", id, name, price, category);
    }
}
