package com.rest.order.exception.handler;

import com.rest.order.constants.ErrorConstants;
import com.rest.order.exception.DetailedMessage;
import com.rest.order.exception.ErrorDetails;
import com.rest.order.exception.SupernovaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SupernovaException.class)
    public ResponseEntity<ErrorDetails> handleNoSuchElementException(SupernovaException supernovaException) {
        return new ResponseEntity<>(
                new ErrorDetails(supernovaException.getErrorCode(), supernovaException.getMessage(),
                        supernovaException.getDetailedMessageList(), new Date()),
                supernovaException.getHttpStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalExceptionHandling(Exception exception, WebRequest request) {
        List<DetailedMessage> detailedMessageList = new ArrayList<>();
        DetailedMessage detailedMessage = new DetailedMessage("", HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                exception.getMessage());
        detailedMessageList.add(detailedMessage);
        return new ResponseEntity<>(new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                ErrorConstants.INTERNAL_SERVER_ERROR_MSG, detailedMessageList, new Date()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
