package com.api.bookings.exceptionhandler;

import com.api.bookings.exceptionhandler.exception.BookingNameException;
import com.api.bookings.exceptionhandler.exception.OfficeNameException;
import com.api.bookings.exceptionhandler.exception.PlaceNameException;
import com.api.bookings.exceptionhandler.exception.UserNameException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class WorkstationExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({BookingNameException.class})
    public ResponseEntity<Object> handleBookingsNameException(BookingNameException ex, WebRequest request){
        String mensagemUsuario = messageSource.getMessage("bookings.name-invalido-ou-existente", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({UserNameException.class})
    public ResponseEntity<Object> handleUserNameException(UserNameException ex, WebRequest request){
        String mensagemUsuario = messageSource.getMessage("office.name-invalido-ou-existente", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({OfficeNameException.class})
    public ResponseEntity<Object> handleOfficeNameException(OfficeNameException ex, WebRequest request){
        String mensagemUsuario = messageSource.getMessage("office.name-invalido-ou-existente", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({PlaceNameException.class})
    public ResponseEntity<Object> handleWorkstationNameException(PlaceNameException ex, WebRequest request){
        String mensagemUsuario = messageSource.getMessage("workstation.name-invalido-ou-existente", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Erro> erros = criarListaDeErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    private List<Erro> criarListaDeErros(BindingResult bindingResult){
        List<Erro> erros = new ArrayList<>();

        for(FieldError fieldError : bindingResult.getFieldErrors()) {
            String mensagemUsuario=messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String mensagemDesenvolvedor=fieldError.toString();
            erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        }

        return erros;
    }


    @Getter
    public static class Erro{
        private String mensagemUsuario;
        private String mensagemDesenvolvedor;

        public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
            this.mensagemUsuario = mensagemUsuario;
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
        }


    }

}