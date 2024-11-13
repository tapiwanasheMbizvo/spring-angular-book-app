package com.tapiwa.book.social.custom;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Map;

@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpResponse {
    private final int httpStatusCode;
    private final String httpStatusMessage;
    private final Map<?, ?> body;
    private final LocalDateTime timestamp;


}
