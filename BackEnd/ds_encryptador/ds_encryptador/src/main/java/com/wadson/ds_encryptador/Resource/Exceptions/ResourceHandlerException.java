package com.wadson.ds_encryptador.Resource.Exceptions;

import com.wadson.ds_encryptador.Service.Exceptions.DatabaseException;
import com.wadson.ds_encryptador.Service.Exceptions.MessageNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceHandlerException {

    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<StandartError> messageNotFound(HttpServletRequest request, MessageNotFoundException err) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandartError stError = new StandartError();
        stError.setMessage(err.getMessage());
        stError.setErr("Resource not Found");
        stError.setPath(request.getServletPath());
        stError.setStatus(status.value());
        stError.setTimestamp(Instant.now());
        return ResponseEntity.status(status).body(stError);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandartError> dbError(HttpServletRequest request, DatabaseException dbException) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandartError stError = new StandartError();
        stError.setTimestamp(Instant.now());
        stError.setPath(request.getServletPath());
        stError.setErr("Database exception!");
        stError.setMessage(dbException.getMessage());
        stError.setStatus(status.value());
        return ResponseEntity.status(status.value()).body(stError);
    }


}
