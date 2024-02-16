package com.wadson.ds_encryptador.Resource;

import com.wadson.ds_encryptador.DTO.MessageDTO;
import com.wadson.ds_encryptador.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/message")
public class MessageResource {

    @Autowired
    private MessageService messageServ;

    @GetMapping
    public ResponseEntity<List<MessageDTO>> getAll() {
        List<MessageDTO> listMessages = messageServ.findAll();
        return ResponseEntity.ok().body(listMessages);
    }


}
