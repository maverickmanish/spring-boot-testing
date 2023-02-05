package com.rest.order.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorDetails {

    private String errorCode;
    private String errorMessage;
    private List<DetailedMessage> detailedMessage;
    private Date timestamp;
}
