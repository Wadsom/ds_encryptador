package com.wadson.ds_encryptador.Repository;

import com.wadson.ds_encryptador.Entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity,Long> {

}
