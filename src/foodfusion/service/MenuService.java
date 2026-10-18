package foodfusion.service;

import foodfusion.model.FoodItem;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MenuService {
    private final List<FoodItem> menu = new ArrayList<>();

    public MenuService() {
        menu.add(new FoodItem(1, "Biryani", 220, "Main"));
        menu.add(new FoodItem(2, "Pizza", 180, "Main"));
        menu.add(new FoodItem(3, "Burger", 100, "Snack"));
        menu.add(new FoodItem(4, "Pasta", 150, "Main"));
        menu.add(new FoodItem(5, "Brownie", 90, "Dessert"));
    }

    public List<FoodItem> getAll() { return new ArrayList<>(menu); }

    public Optional<FoodItem> findById(int id) {
        return menu.stream().filter(item -> item.getId() == id).findFirst();
    }

    public List<FoodItem> searchByName(String search) {
        return menu.stream()
                .filter(item -> item.getName().toLowerCase().contains(search.toLowerCase()))
                .toList();
    }

    public List<FoodItem> sortedByPrice() {
        return menu.stream().sorted(Comparator.comparingDouble(FoodItem::getPrice)).toList();
    }
}
