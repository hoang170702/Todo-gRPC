package com.bank.todoserver.gRPCService;

import com.bank.todoproto.Empty;
import com.bank.todoproto.TodoReq;
import com.bank.todoproto.TodoRes;
import com.bank.todoproto.TodoServiceGrpc;
import com.bank.todoserver.service.ITodoService;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TodoGrpcService extends TodoServiceGrpc.TodoServiceImplBase {

    @Autowired
    private ITodoService iTodoService;

    @Override
    public void addOrUpdateOrDel(TodoReq request, StreamObserver<TodoRes> responseObserver) {
        iTodoService.addOrUpdateOrDel(request.getReqId(), request.getTodo(), request.getIsDel(), responseObserver);
    }

    @Override
    public void getTodos(Empty request, StreamObserver<TodoRes> responseObserver) {
        responseObserver.onNext(
                TodoRes.newBuilder()
                        .setResId(UUID.randomUUID().toString())
                        .setResMessage("find all success")
                        .setTodos(iTodoService.findAll()).build()
        );
        responseObserver.onCompleted();
    }
}
