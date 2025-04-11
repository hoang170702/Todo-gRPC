package com.bank.todoclient.service;

import com.bank.todoproto.TodoServiceGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoClientService {

    private final TodoServiceGrpc.TodoServiceBlockingStub stub;

    @Autowired
    public TodoClientService(TodoServiceGrpc.TodoServiceBlockingStub stub) {
        this.stub = stub;
    }


}
