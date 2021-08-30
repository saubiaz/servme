package com.servme.question1.one;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String name;
    private String address;
    private String school;
    private String clazz;
    private double fee;
}
