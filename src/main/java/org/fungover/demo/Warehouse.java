package org.fungover.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Warehouse {
    private List<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        var p = warehouse.productById("1234");
        var product = p.orElse(new Product(""));
    }

    public List<Product> allProducts() {
        return products;
    }

    public Optional<Product> productById(String id) {
        return products.stream()
                .filter(p -> p.id().equals(id))
                .findFirst();
    }
}

record Product(String id) {
}

class NoSuchProductIdException extends RuntimeException {
}
