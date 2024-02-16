package com.wadson.ds_encryptador.DTO;

import com.wadson.ds_encryptador.Entity.MessageEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MessageDTO {
    private Long id;
    @NotNull
    @NotBlank
    private String text;

    public MessageDTO() {
    }

    public MessageDTO(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public MessageDTO(MessageEntity entity) {
        this.id = entity.getId();
        this.text = entity.getText();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
