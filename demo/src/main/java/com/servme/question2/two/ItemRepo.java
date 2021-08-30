package com.servme.question2.two;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ItemRepo {

    private Set<Item> items = new HashSet<>();

    public void putItem(Item item) {
        items.add(item);
    }

    public void removeItemById(int itemId) {
        items
            .stream()
            .filter(item -> item.getId() == (itemId))
            .findFirst()
            .map(it -> {
                items.remove(it);
                return it;
            });
    }

    public Item getItemById(int itemId) {
        Optional<Item> selectedItem =  items.stream().filter( item -> item.getId()==itemId).findFirst();
        return selectedItem.orElse(null);
    }

}
