    package com.bank.todoserver.service;

    import com.bank.todoproto.Todo;
    import com.bank.todoproto.TodoList;
    import com.bank.todoproto.TodoRes;
    import io.grpc.stub.StreamObserver;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;
    import java.util.UUID;
    import java.util.concurrent.CopyOnWriteArrayList;

    @Service
    public class TodoService implements ITodoService {

        private final List<Todo> todos = new CopyOnWriteArrayList<>();

        @Override
        public void addOrUpdateOrDel(String reqId,Todo todo, boolean isDel, StreamObserver<TodoRes> responseObserver) {
            try {
                Optional<Todo> existTodo = todos.stream().filter(t -> t.getId() == todo.getId()).findFirst();
                if (existTodo.isPresent()) {
                    if (isDel) {
                        todos.remove(existTodo.get());
                        responseObserver.onNext(
                                TodoRes.newBuilder()
                                        .setResId(reqId)
                                        .setResMessage("delete success")
                                        .build()
                        );
                        responseObserver.onCompleted();
                        return;
                    }
                    Todo updateTodo = Todo.newBuilder().setTitle(todo.getTitle()).build();
                    todos.set(todos.indexOf(existTodo.get()), updateTodo);
                    responseObserver.onNext(
                            TodoRes.newBuilder()
                                    .setResId(reqId)
                                    .setResMessage("update success")
                                    .build()
                    );
                    responseObserver.onCompleted();
                    return;
                }
                Todo newTodo = Todo.newBuilder().setId(todo.getId()).setTitle(todo.getTitle()).build();
                todos.add(newTodo);
                responseObserver.onNext(
                        TodoRes.newBuilder()
                                .setResId(reqId)
                                .setResMessage("add success")
                                .build()
                );
                responseObserver.onCompleted();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public TodoList findAll() {
            try {
                if (todos.isEmpty()) {
                     return null;
                }
               return TodoList.newBuilder().addAllTodos(todos).build();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }
