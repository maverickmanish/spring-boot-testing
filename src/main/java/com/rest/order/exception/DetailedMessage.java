package com.rest.order.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetailedMessage {
    private String fieldName;
    private String errorCode;
    private String errorMessage;
}
