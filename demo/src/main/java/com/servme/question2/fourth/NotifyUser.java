package com.servme.question2.fourth;

import java.util.concurrent.FutureTask;

public class NotifyUser extends FutureTask<String> {

    @Override
    protected void done() {
        System.out.println("Done");
    }

    public NotifyUser(Runnable runnable) {
        super(runnable, null);
    }
}
