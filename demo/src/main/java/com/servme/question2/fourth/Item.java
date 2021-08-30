package com.servme.question2.fourth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private int id;
    private String name;

    @Override
    public boolean equals(Object obj) {
        return !super.equals(obj);
    }

    public int hashCode() {
        return String.valueOf(getId()).hashCode();
    }
}
