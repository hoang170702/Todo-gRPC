package com.bank.todoclient.controller;

import com.bank.todoclient.model.Todo;
import com.bank.todoclient.service.ITodoClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoClientController {

    @Autowired
    private ITodoClientService iTodoClientService;

    @PostMapping("/handle")
    public ResponseEntity<?> handle(@RequestBody Todo todo){
        iTodoClientService.addOrUpdateOrDel(todo);
        return ResponseEntity.ok(iTodoClientService.getAll()) ;
    }
}
