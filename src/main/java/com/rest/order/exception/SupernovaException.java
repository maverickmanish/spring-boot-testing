package com.rest.order.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
public class SupernovaException extends RuntimeException {
    private String errorCode;
    private String message;
    private List<DetailedMessage> detailedMessageList;
    private HttpStatus httpStatusCode;

    public SupernovaException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatusCode = HttpStatus.UNPROCESSABLE_ENTITY;
        this.detailedMessageList = new ArrayList<>();
    }

    public SupernovaException(String errorCode, String message, List<DetailedMessage> detailedMessageList) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
        this.detailedMessageList = detailedMessageList;
        this.httpStatusCode = HttpStatus.UNPROCESSABLE_ENTITY;
    }

    public SupernovaException(String errorCode, String message, List<DetailedMessage> detailedMessageList,
            HttpStatus httpStatusCode) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
        if (detailedMessageList != null && !detailedMessageList.isEmpty()) {
            this.detailedMessageList = detailedMessageList;
        }
        this.httpStatusCode = httpStatusCode;
    }
}
