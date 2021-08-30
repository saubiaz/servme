package com.servme.question2.seventh;

import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class Main {

    @Autowired
    ItemRepo itemRepo;

    public void main(String args[]) {
        Item item = new Item(1002, "Joe");
        itemRepo.putItem(item);
      AutoCloseable closeable =  itemRepo.addListener(itemRepo);
    }
}
