package com.servme.question1.fourth;

import com.servme.question1.one.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepo {

    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student s) {
        students.add(s);
    }

    public Student searchByName(String name) {

        Optional<Student> student = students
            .stream()
            .filter(s -> s
                .getName()
                .equalsIgnoreCase(name))
            .findFirst();

        return student.orElse(null);
    }
}
