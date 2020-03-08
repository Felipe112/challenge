package com.wolox.challenge.service.configuration;

import javax.persistence.PersistenceException;

import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wolox.challenge.service.dto.Response;


@RestController
public class AbstractRestController {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public Response<Void> tratarErrores(Throwable e) {
        if (e instanceof JpaSystemException || e instanceof PersistenceException) {
            return new Response<>("Hubo un error, intente nuevamente o contacte con el administrador", false);
        }
        return new Response<>(e);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public Response<Void> argumentoErroneo(Exception oops) {
        final Response<Void> resultado = new Response<>();
        resultado.setState(Boolean.FALSE);
        resultado.setMessage(String.valueOf(oops.getLocalizedMessage()));
        return resultado;
    }

}
