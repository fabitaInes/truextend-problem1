package com.truextend.problem1.exception.advice;

import com.truextend.problem1.exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Student not found advice.
 */
@ControllerAdvice
class StudentNotFoundAdvice {

    /**
     * Handler map.
     *
     * @param ex the ex
     * @return the map
     */
    @ResponseBody
    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Map<?, ?> handler(StudentNotFoundException ex) {
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("message", ex.getMessage());
        return messageMap;
    }
}