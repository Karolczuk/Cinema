package com.app.cinema.exceptions;

import java.time.LocalDateTime;

public class AppException extends RuntimeException {

    private ExceptionInfo exceptionInfo;

    public AppException(String exceptionMessage) {
        this.exceptionInfo = ExceptionInfo.builder()
                .message(exceptionMessage)
                .dateTime(LocalDateTime.now())
                .build();
    }

    public ExceptionInfo getExceptionInfo() {
        return exceptionInfo;
    }
}
