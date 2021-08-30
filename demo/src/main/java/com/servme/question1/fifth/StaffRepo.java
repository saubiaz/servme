package com.servme.question1.fifth;

import java.util.List;
import java.util.Optional;

public class StaffRepo {

    public Staff searchByName(String name, List<Staff> staffs) {

        Optional<Staff> staff = staffs
            .stream()
            .filter(s -> s
                .getName()
                .equalsIgnoreCase(name))
            .findFirst();

        return staff.orElse(null);
    }

}
