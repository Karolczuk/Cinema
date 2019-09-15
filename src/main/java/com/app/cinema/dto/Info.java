package com.app.cinema.dto;

import com.app.cinema.exceptions.ExceptionInfo;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Info<T> {

    // @Builder.Default
    T data; // = null;

    // @Builder.Default
    ExceptionInfo exception; // = null;
}
