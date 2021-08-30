package com.servme.question2.fifth;

import com.servme.question2.fourth.NotifyUser;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ItemRepo {

    private static Set<Item> items = new HashSet<>();

    public static void putItem(Item item) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture.runAsync(() -> actualPutItem(item)).thenRun(()-> notifyUser("Done"));
        executor.shutdown();
    }

    private static void notifyUser(String callback) {
        System.out.println(callback);
    }

    private static void actualPutItem(Item item) {
        try {
            Thread.sleep(2000);
            System.out.println("Entered");
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
