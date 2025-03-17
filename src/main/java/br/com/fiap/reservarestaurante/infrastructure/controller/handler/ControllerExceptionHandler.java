package br.com.fiap.reservarestaurante.infrastructure.controller.handler;

import br.com.fiap.reservarestaurante.infrastructure.controller.response.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ServiceResponse<Void>> handleIllegalArgumentException(IllegalArgumentException exception) {
        return new ResponseEntity<>(ServiceResponse.build(new ServiceResponse.Message(-1, exception.getMessage())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ServiceResponse<Void>> handleIllegalStateException(IllegalStateException exception) {
        return new ResponseEntity<>(ServiceResponse.build(new ServiceResponse.Message(-2, exception.getMessage())), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
