package com.servme.question2.one;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ItemRepo {
    private Set<Item> items = new HashSet<>();

    public void putItem(Item item) {

        items.add(item);
    }

    public void removeItemById(int itemId) {
        getFirst(itemId)
            .map(it -> {
                items.remove(it);
                return it;
            });
    }

    public Item getItemById(int itemId) {
        Optional<Item> selectedItem = getFirst(itemId);
        return selectedItem.orElse(null);
    }

    private Optional<Item> getFirst(int itemId) {
        return items
            .stream()
            .filter(item -> item.getId() == (itemId))
            .findFirst();
    }


}
