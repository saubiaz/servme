package com.servme.question2.seventh;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class ItemRepo implements Listener {

    private final Set<Item>      items     = new HashSet<>();
    private final List<Listener> listeners = new CopyOnWriteArrayList<>();

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

    public  AutoCloseable addListener(Listener listener) {
        listeners.add(listener);
       return new ListenerCloseable<>(listener,listeners);
    }



    private static final class ListenerCloseable<T> implements AutoCloseable {
        final T f;
        final List<T> list;

        private ListenerCloseable(T f, List<T> list) {
            this.f = f;
            this.list = list;
        }

        public void close() throws Exception {
            this.list.remove(this.f);
        }
    }

}
