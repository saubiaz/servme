package com.servme.question2.sixth;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ItemRepo implements Listener {

    private Set<Item>      items     = new HashSet<>();
    private List<Listener> listeners = new ArrayList<>();

    public void putItem(Item item) {
        items.add(item);

        // Notify everybody that may be interested.
        for (Listener eventListener : listeners) {
            eventListener.itemPut(item);
        }
    }

    public void removeItemById(int itemId) {
        Optional<Item> item = getFirst(itemId)
            .map(it -> {
                items.remove(it);
                return it;
            });

        // Notify everybody that may be interested.
        for (Listener eventListener : listeners) {
            eventListener.itemRemoved(item.orElse(null));
        }
    }

    private Optional<Item> getFirst(int itemId) {
        return items
            .stream()
            .filter(item -> item.getId() == (itemId))
            .findFirst();
    }

    @Override
    public void itemPut(Item item) {
        System.out.println("Item added" + " " + item);
    }

    @Override
    public void itemRemoved(Item item) {

        System.out.println("Item Removed" + " " + item);

    }

    public synchronized void addListener(Listener listener) {
        listeners.add(listener);
    }
}
