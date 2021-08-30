package com.servme.question1.fifth;

import com.servme.question1.one.Student;
import java.util.List;
import java.util.Optional;

public class StudentRepo {

    public Student searchByName(String name, List<Student> students) {

        Optional<Student> student = students
            .stream()
            .filter(s -> s
                .getName()
                .equalsIgnoreCase(name))
            .findFirst();

        return student.orElse(null);
    }
}
