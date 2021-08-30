package com.servme.question2.two;

public class Main {

    public static void main(String[] args) {

        ItemRepo repo = new ItemRepo();
        repo.putItem(new Item(1001,"Joe"));
        // Name 'Joe' was wrong name so we want to fix it as 'Jonathan' repo.putItem(new Item(1001,"Jonathan"));
        repo.putItem(new Item(1001,"Jonathan"));

        System.out.println(repo.getItemById(1001));
    }
}
