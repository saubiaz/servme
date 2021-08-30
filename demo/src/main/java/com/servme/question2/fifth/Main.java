package com.servme.question2.fifth;

public class Main {

    public static void main(String args[]) {
        ItemRepo.putItem(new Item(1002, "Joe"));
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
