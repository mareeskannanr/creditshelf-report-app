package com.challenge.app.advice;

import com.challenge.app.exceptions.AppException;
import com.challenge.app.utils.AppUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    /**
     * This method used to handle exception related to application
     * @param exception
     * @return
     */
    @ExceptionHandler(AppException.class)
    public ResponseEntity handleAppException(AppException exception) {
        JsonNode response = AppUtils.generateResponse(true, exception.getMessages());
        return ResponseEntity.status(exception.getStatusCode()).body(response);
    }

}
