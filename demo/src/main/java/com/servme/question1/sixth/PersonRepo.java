package com.servme.question1.sixth;

import java.util.List;
import java.util.Optional;

public class PersonRepo {

    public <T> T searchByName(String name, List<T> listOfNames) {

        Optional<T> selectedPerson = listOfNames
            .stream()
            .filter(s -> s
                .getClass()
                .getName()
                .equalsIgnoreCase(name))
            .findFirst();

        return selectedPerson.orElse(null);
    }
}
