package com.tapiwa.book.social.custom;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpResponse {
    protected final HttpStatus httpStatusCode;
    protected final String httpStatusMessage;
    protected final Map<?, ?> body;
    protected final LocalDateTime timestamp;


}
