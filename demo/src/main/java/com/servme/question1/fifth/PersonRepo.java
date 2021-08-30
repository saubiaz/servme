package com.servme.question1.fifth;

import com.servme.question1.one.Student;
import java.util.ArrayList;
import java.util.List;

public class PersonRepo {

    private List<Staff> staffs = new ArrayList<>();

    public void addStaff(Staff s) {
        staffs.add(s);
    }

    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student s) {
        students.add(s);
    }

}
