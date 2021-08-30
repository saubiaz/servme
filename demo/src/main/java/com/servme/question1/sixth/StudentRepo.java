package com.servme.question1.sixth;

import com.servme.question1.one.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentRepo {

    @Autowired
    private PersonRepo personRepo;

    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student s) {
        students.add(s);
    }

    public Student searchByName(String name) {

        return personRepo.searchByName(name,students);
    }
}
