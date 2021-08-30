package com.servme.todo.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TodoStatus {
    Completed,
    Initial,
    Overdue,
    Snoozed,
    Started

}
