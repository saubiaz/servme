package com.servme.question1.one;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StaffRepo {

    private List<Staff> staffs = new ArrayList<>();

    public void addStaff(Staff s) {
        staffs.add(s);
    }

    public Staff searchByName(String name) {
        Optional<Staff> staff =  staffs.stream().filter(s -> s.getName().equalsIgnoreCase(name)).findFirst();

        return staff.orElse(null);
    }
}
