package foodfusion.service;

import foodfusion.model.Order;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;

public class OrderFileService {
    private final Path file = Path.of("foodfusion_orders.txt");

    public void save(List<Order> orders) throws IOException {
        List<String> lines = orders.stream().map(Order::toCsv).toList();
        Files.write(file, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void displaySavedOrders() throws IOException {
        if (!Files.exists(file)) {
            System.out.println("No saved order file yet. Choose Save Orders first.");
            return;
        }
        try (Stream<String> lines = Files.lines(file)) {
            lines.forEach(System.out::println);
        }
    }

    public Path getFilePath() { return file.toAbsolutePath(); }
}
