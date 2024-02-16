package com.wadson.ds_encryptador.Service;

import com.wadson.ds_encryptador.DTO.MessageDTO;
import com.wadson.ds_encryptador.Entity.MessageEntity;
import com.wadson.ds_encryptador.Repository.MessageRepository;
import com.wadson.ds_encryptador.Service.Exceptions.DatabaseException;
import com.wadson.ds_encryptador.Service.Exceptions.MessageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepo;


    @Transactional(readOnly = true)
    public List<MessageDTO> findAll() {
        List<MessageEntity> listEntity = messageRepo.findAll();
        List<MessageDTO> listDTO = listEntity.stream().map(MessageDTO::new).toList();
        return listDTO;
    }

    @Transactional(readOnly = true)
    public MessageDTO findByOne(Long id) {
        Optional<MessageEntity> item = messageRepo.findById(id);
        MessageEntity sms = item.orElseThrow(() -> new MessageNotFoundException("Messagem não encontrada!"));
        return new MessageDTO(sms);
    }

    @Transactional
    public MessageDTO postMethod(MessageDTO dto) {

        try {
            MessageEntity sms = new MessageEntity();
            sms.setText(dto.getText());
            sms = messageRepo.save(sms);
            return new MessageDTO(sms);
        } catch (DatabaseException e) {
            throw new DatabaseException("Integration Violation");
        }
    }
}
