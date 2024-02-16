package com.wadson.ds_encryptador.Service;

import com.wadson.ds_encryptador.DTO.MessageDTO;
import com.wadson.ds_encryptador.Entity.MessageEntity;
import com.wadson.ds_encryptador.Repository.MessageRepository;
import com.wadson.ds_encryptador.Service.Exceptions.DatabaseException;
import com.wadson.ds_encryptador.Service.Exceptions.MessageNotFoundException;
import org.hibernate.dialect.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
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

    @Transactional
    public MessageDTO putMethod(Long id, MessageDTO dto) {
        if (!messageRepo.existsById(id)) throw new MessageNotFoundException("Está messagem não existe!");
        try {
            MessageEntity item = messageRepo.getReferenceById(id);
            item.setText(dto.getText());
            item = messageRepo.save(item);
            return new MessageDTO(item);
        } catch (DatabaseException e) {
            throw new DatabaseException("database violation!!");
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteMethod(Long id) {
        if (!messageRepo.existsById(id)) throw new MessageNotFoundException("Message not found!");
        try {
            messageRepo.deleteById(id);
        } catch (DatabaseException e) {
            throw new DatabaseException("Integration Violation!");
        }
    }
}
