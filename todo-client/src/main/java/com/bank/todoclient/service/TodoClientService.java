package com.bank.todoclient.service;

import com.bank.todoproto.TodoServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class TodoClientService {

    private final TodoServiceGrpc.TodoServiceBlockingStub stub;

    public TodoClientService() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        stub = TodoServiceGrpc.newBlockingStub(channel);
    }
}
