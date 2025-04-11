package com.bank.todoclient.service;

import com.bank.todoclient.model.Todo;
import com.bank.todoproto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TodoClientService implements ITodoClientService {

    private final TodoServiceGrpc.TodoServiceBlockingStub stub;

    @Autowired
    public TodoClientService(TodoServiceGrpc.TodoServiceBlockingStub stub) {
        this.stub = stub;
    }


    @Override
    public void addOrUpdateOrDel(Todo todo) {
        TodoReq todoReq = TodoReq.newBuilder()
                .setReqId(UUID.randomUUID().toString())
                .setTodo(
                        com.bank.todoproto.Todo.newBuilder()
                                .setId(todo.getId())
                                .setTitle(todo.getTitle())
                ).setIsDel(todo.getIsDel()).build();
        stub.addOrUpdateOrDel(todoReq);
    }

    @Override
    public List<Todo> getAll() {
       TodoRes todoRes= stub.getTodos(Empty.newBuilder().build());
        List<Todo> todos = new ArrayList<>();

        for (com.bank.todoproto.Todo todo : todoRes.getTodos().getTodosList()){
            Todo cTodo = new Todo();
            cTodo.setId(todo.getId());
            cTodo.setTitle(todo.getTitle());
            todos.add(cTodo);
        }
        return todos;
    }
}
