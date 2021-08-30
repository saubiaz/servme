package com.servme.question2.fourth;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ItemRepo {

    private static Set<Item> items = new HashSet<>();

    public static void putItem(Item item, String callback) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<String> future = new NotifyUser(() -> actualPutItem(item));
        executor.submit(future);
        try {
            future.get();
            notifyUser(callback);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        executor.shutdown();
    }

    private static void notifyUser(String callback) {
        System.out.println(callback);
    }

    private static void actualPutItem(Item item) {
        try {
            Thread.sleep(2000);
            items.add(item);
        }
        catch (InterruptedException abort) {
            Thread
                .currentThread()
                .interrupt();
            System.out.println(item.getName() + " interrupted.");
        }
    }

}
