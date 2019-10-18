package com.truextend.problem1.exception.advice;

import com.truextend.problem1.exception.ClazzFieldsException;
import com.truextend.problem1.exception.StudentFieldsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Student fields advice.
 */
@ControllerAdvice
class StudentFieldsAdvice {

    /**
     * Handler map.
     *
     * @param ex the ex
     * @return the map
     */
    @ResponseBody
    @ExceptionHandler(StudentFieldsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<?, ?> handler(ClazzFieldsException ex) {
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("message", ex.getMessage());
        return messageMap;
    }
}