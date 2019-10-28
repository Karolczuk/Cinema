package com.app.cinema.exceptions;

import java.time.LocalDateTime;

public class AppException extends RuntimeException {

    private ExceptionInfo exceptionInfo;

    public AppException(String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionInfo = ExceptionInfo.builder()
                .message(exceptionMessage)
                .dateTime(LocalDateTime.now())
                .build();
    }


//    public AppException(String message) {
//        super(message);
//    }


    public ExceptionInfo getExceptionInfo() {
        return exceptionInfo;
    }
}
