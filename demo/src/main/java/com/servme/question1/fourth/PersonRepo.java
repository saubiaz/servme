package com.servme.question1.fourth;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonRepo {

    private List<Staff> staffs = new ArrayList<>();

    public void addStaff(Staff s) {
        staffs.add(s);
    }

}
