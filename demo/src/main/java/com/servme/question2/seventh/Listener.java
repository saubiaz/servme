package com.servme.question2.seventh;

import org.infinispan.functional.Listeners;

public interface Listener {
    void itemPut(Item item);
    void itemRemoved(Item item);
    AutoCloseable addListener(Listener listener);
}
