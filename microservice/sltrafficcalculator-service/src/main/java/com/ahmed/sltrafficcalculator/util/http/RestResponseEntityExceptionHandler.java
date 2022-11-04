package com.ahmed.sltrafficcalculator.util.http;

import com.ahmed.sltrafficcalculator.util.exceptions.InvalidInputException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Iterator;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class RestResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { InvalidInputException.class, IllegalArgumentException.class, IllegalStateException.class, MethodArgumentTypeMismatchException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        HttpErrorInfo httpErrorInfo = createHttpErrorInfo(BAD_REQUEST, request, ex);
        return handleExceptionInternal(ex, httpErrorInfo,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private HttpErrorInfo createHttpErrorInfo(HttpStatus httpStatus, WebRequest request, Exception ex) {

        final String path = ((ServletWebRequest)request).getRequest().getRequestURI().toString() + createParamPath(request);
        final String message = ex.getMessage();

        return new HttpErrorInfo(httpStatus, path, message);
    }

    private String createParamPath(WebRequest request){
        String path = "";
        Iterator<String> parameterNames = request.getParameterNames();
        if(parameterNames.hasNext()){
            path = "?";
        }
        while(parameterNames.hasNext()){
            String parameterName = parameterNames.next();
            String parameterValue = request.getParameterValues(parameterName)[0];
            path+=parameterName+"="+parameterValue;
        }
        return path;
    }
}
