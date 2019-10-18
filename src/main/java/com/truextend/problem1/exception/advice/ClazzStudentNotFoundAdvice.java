package com.truextend.problem1.exception.advice;

import com.truextend.problem1.exception.ClazzStudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Clazz student not found advice.
 */
@ControllerAdvice
class ClazzStudentNotFoundAdvice {

    /**
     * Handler map.
     *
     * @param ex the ex
     * @return the map
     */
    @ResponseBody
    @ExceptionHandler(ClazzStudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Map<?, ?> handler(ClazzStudentNotFoundException ex) {
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("message", ex.getMessage());
        return messageMap;
    }
}