package com.vinnivso.monolythicspring.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vinnivso.monolythicspring.model.error.ErrorMessage;
import com.vinnivso.monolythicspring.model.exception.ResourceNotFoundException;

//Criamos uma classe que serve para ser um Controlador de qualquer Erro dentro de uma API REST.
@ControllerAdvice
public class RestExceptionHandler {
  // Se o erro for do tipo "ResourceNotFoundException", ele vai executar o método.
  @ExceptionHandler(ResourceNotFoundException.class)
  // Esse "?", significa que pode ser qualquer coisa, não existe uma tipagem
  // especificada.
  public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
    ErrorMessage error = new ErrorMessage("Not Found", HttpStatus.NOT_FOUND.value(), exception.getMessage());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }
}
