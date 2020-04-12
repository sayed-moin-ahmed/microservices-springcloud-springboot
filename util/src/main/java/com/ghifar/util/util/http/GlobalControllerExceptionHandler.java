package com.ghifar.util.util.http;

import com.ghifar.util.util.exceptions.InvalidInputException;
import com.ghifar.util.util.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;


@RestControllerAdvice
/*A new @RestControllerAdvice is provided for exception handling, it is combination of @ControllerAdvice
 and @ResponseBody. You can remove the @ResponseBody on the @ExceptionHandler method when use this new annotation.!*/
public class GlobalControllerExceptionHandler {

    private static final Logger LOG= LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public @ResponseBody HttpErrorInfo handleNotFoundExceptions(ServerHttpRequest request, Exception ex){
        return createHttpErrorInfo(HttpStatus.NOT_FOUND, request,ex);
    }

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(InvalidInputException.class)
    public @ResponseBody HttpErrorInfo handleInvalidInputException(ServerHttpRequest request, Exception e){
        return createHttpErrorInfo(HttpStatus.UNPROCESSABLE_ENTITY, request,e);
    }


    private HttpErrorInfo createHttpErrorInfo(HttpStatus httpStatus, ServerHttpRequest request, Exception ex){
        final String path= request.getPath().pathWithinApplication().value();
        final String message= ex.getMessage();
        LOG.debug("returning HTTP status: {} for path: {}, message: {}",httpStatus,path,message);
        return new HttpErrorInfo(path,httpStatus,message);
    }
}
