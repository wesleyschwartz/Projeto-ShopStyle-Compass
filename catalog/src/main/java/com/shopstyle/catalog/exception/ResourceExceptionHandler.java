package com.shopstyle.catalog.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class ResourceExceptionHandler{

/*
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> httpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        JsonMappingException jme = (JsonMappingException) ex.getCause();
        String campo = jme.getPath().get(0).getFieldName();
        String msg2 = "";
        String msg = "";
        if (campo.equals("sex")) {
            msg = "Valid values: Masculino || Feminino";
        } else if (campo.equals("birthdate")) {
            msg = "Format valid: dd/MM/yyyy";
        }
        StandardError error = new StandardError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "camp: " + campo + " invalid. " + msg,
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /*@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> httpMessageNotReadableException(DataIntegrityViolationException ex, HttpServletRequest request) {
        var exceptionCause = ex.getMostSpecificCause();
        if (exceptionCause instanceof PSQLException psqlException) {
            var serverErrorMessage = psqlException.getServerErrorMessage();
            if (serverErrorMessage != null) {
                var detail = serverErrorMessage.getDetail();
                exceptionCause = new RuntimeException(detail);
            }
        }
        StandardError error = new StandardError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                exceptionCause.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }*/
/*
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<StandardError> methodArgumentNotValidException(NoSuchElementException ex, HttpServletRequest request) {

        StandardError error = new StandardError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }*/

}