package com.servme.question1.sixth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public  class Person {

    private String name;
    private String address;
    private String school;
    private String clazz;
}
