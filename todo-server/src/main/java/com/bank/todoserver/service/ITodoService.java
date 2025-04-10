package com.bank.todoserver.service;

import com.bank.todoproto.Todo;
import com.bank.todoproto.TodoList;
import com.bank.todoproto.TodoRes;
import io.grpc.stub.StreamObserver;

public interface ITodoService {
    public void addOrUpdateOrDel(String reqId,Todo todo, boolean isDel, StreamObserver<TodoRes> responseObserver);
    public TodoList findAll();
}
