package com.bank.todoclient.service;

import com.bank.todoclient.model.Todo;
import com.bank.todoproto.TodoList;
import com.bank.todoproto.TodoRes;

public interface ITodoClientService {
    public void addOrUpdateOrDel(Todo todo);
    public TodoRes getAll();
}
