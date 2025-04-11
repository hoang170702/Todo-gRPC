package com.bank.todoclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Todo {
    private int id;
    private String title;
    private Boolean isDel;
}
