package com.servme.question2.sixth;

import org.springframework.beans.factory.annotation.Autowired;

public class Main {

    @Autowired
    ItemRepo itemRepo;

    public void main(String args[]) {
        itemRepo.putItem(new Item(1002, "Joe"));
        itemRepo.addListener(itemRepo);
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
