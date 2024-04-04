package com.coussy.reference.common.http;

import com.coussy.reference.common.configuration.DependencyError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationAdvice {

    // est ce normal de renvoyer au front ce message d'erreur qui émane en fait du service distant ?
    @ExceptionHandler(DependencyError.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ErrorResponseHttp handleDependencyError(DependencyError dependencyError) {
        return new ErrorResponseHttp(dependencyError.getCode());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseHttp handle(IllegalArgumentException illegalArgumentException) {
        return new ErrorResponseHttp(illegalArgumentException.getMessage());
    }

}


