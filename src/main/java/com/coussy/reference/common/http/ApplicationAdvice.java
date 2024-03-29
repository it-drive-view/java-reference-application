package com.coussy.reference.common.http;

import com.coussy.reference.common.configuration.DependencyError;
import com.coussy.reference.common.configuration.DependencyErrorNoException;
import com.coussy.reference.common.configuration.Dependencyson;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationAdvice {

    // est ce normal de renvoyer au front ce message d'erreur qui émane en fait du service distant ?
    @ExceptionHandler(DependencyError.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ErrorResponseHttp handle(DependencyError dependencyError) {
        return new ErrorResponseHttp(dependencyError.getErrorCode());
    }

//    @ExceptionHandler(DependencyError.class)
//    @ResponseStatus(HttpStatus.BAD_GATEWAY)
//    public ErrorResponseHttp handle(Dependencyson dependencySon) {
//        return new ErrorResponseHttp(dependencySon.getErrorCode());
//    }

}


