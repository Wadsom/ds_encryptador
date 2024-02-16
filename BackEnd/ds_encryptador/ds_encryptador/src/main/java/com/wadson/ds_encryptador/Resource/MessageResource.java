package com.wadson.ds_encryptador.Resource;

import com.wadson.ds_encryptador.DTO.MessageDTO;
import com.wadson.ds_encryptador.Service.MessageService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<MessageDTO> getONE(@PathVariable Long id) {
        MessageDTO itemMessage = messageServ.findByOne(id);
        return ResponseEntity.ok().body(itemMessage);
    }

    @PostMapping
    public ResponseEntity<MessageDTO> insertMode(@RequestBody @Valid MessageDTO dto) {
        dto = messageServ.postMethod(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }





}
