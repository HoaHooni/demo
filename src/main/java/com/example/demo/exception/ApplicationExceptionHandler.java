package com.example.demo.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestControllerAdvice
@PropertySource("classpath:error.properties")
public class ApplicationExceptionHandler {

    public final String REGEX_ERR = "(\\{([0-9]*)\\})";

    @Autowired
    private Environment env;

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Map<String, String>> handleException(
            Exception e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message",  this.getErrorMessage(e.getLocalizedMessage()));
        errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({
            BindException.class,
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<Map<String, Object>> handleException(BindException e) {

        List<String> errors = new ArrayList<>();
        e.getFieldErrors()
                .forEach(err -> errors.add(err.getField() + ": " + err.getDefaultMessage()));
        e.getGlobalErrors()
                .forEach(err -> errors.add(err.getObjectName() + ": " + err.getDefaultMessage()));

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", errors);

        errorResponse.put("message",  this.getErrorMessage(e.getLocalizedMessage()));
        errorResponse.put("status", HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HandleException.class})
    public ResponseEntity<Map<String, String>> handleException(
            HandleException e) {

        Map<String, String> errorResponse = new HashMap<>();

        errorResponse.put("message", this.getErrorMessage(e.getLocalizedMessage(), e.arr));
        errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getErrorMessage(String err, Object[] arr){
        String mess = "" + env.getProperty(err);
        if(arr == null || arr.length ==0){
            return mess;
        }
        Map<String, Integer> replaces = new HashMap<>();
        Pattern pattern = Pattern.compile(REGEX_ERR);
        Matcher matcher = pattern.matcher(mess);
        System.out.println("arr:" + Arrays.toString(arr));
        while (matcher.find()) {
            Integer value = Integer.parseInt(matcher.group(2));
            String key = matcher.group(1);
            replaces.put(key, value);
        }
        List<String> arrStr = new ArrayList<>(Arrays.asList(mess.split(" ")));

        List<String> output = arrStr.stream().map(item -> replaces.containsKey(item)? arr[replaces.get(item)].toString() : item ).collect(Collectors.toList());

        return  String.join(" ", output);
    }

    private String getErrorMessage(String err){

        return env.getProperty(err);
    }
}
