package com.wolox.challenge.service.dto;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Data
public class Response<T> {

    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

    protected boolean state;

    protected int idError;

    protected String dateTime;

    protected String message;

    protected T data;

    public Response() {
        this(true,0,new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()),"",null);
    }

    public Response(String message, T data) {
        this(true,0,new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()),message,data);
    }

    public Response(String message) {
        this(true,0,new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()),message,null);
    }

    public Response(String message, boolean isSuccess) {
        this(isSuccess,0,new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()),message,null);
    }

    public Response(Throwable ex) {
        this(false,0,new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()),ex.getMessage(),null);
    }

    public Response(Throwable ex, int idError) {
        this(false,idError,new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()),ex.getMessage(),null);
    }

    public Response(T data) {
        this(true,0,new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()),"",data);
    }

    public Response(boolean state, int idError, String dateTime, String message, T data) {
        this.state = state;
        this.dateTime = dateTime;
        this.message = message;
        this.data = data;
        this.idError = idError;
    }

}
