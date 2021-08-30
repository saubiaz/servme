package com.servme.question1.sixth;

import com.servme.question1.one.Staff;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class StaffRepo {

    @Autowired
    private PersonRepo personRepo;

    private List<Staff> staffs = new ArrayList<>();

    public void addStaff(Staff s) {
        staffs.add(s);
    }

    public Staff searchByName(String name) {

        return personRepo.searchByName(name, staffs);

    }
}
