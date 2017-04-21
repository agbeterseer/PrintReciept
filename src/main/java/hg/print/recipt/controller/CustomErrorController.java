package hg.print.recipt.controller;

 
import hg.print.recipt.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import javax.validation.ConstraintViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Created by Terseer on 03/14/17.
 */
@ControllerAdvice
public class CustomErrorController {


    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse constraintViolationException(ConstraintViolationException ex) {
        return new ApiErrorResponse("failed", 500, ex.getMessage());
    }

    @ExceptionHandler(value = {NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse noHandlerFoundException(Exception ex) {
        return new ApiErrorResponse("failed", 404, ex.getMessage());
    }

    @ExceptionHandler(value = {HttpMediaTypeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse badRequestException(Exception ex) {
        return new ApiErrorResponse("failed", 4004, ex.getMessage());
    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse mediaTypeNotSupported(Exception ex) {
        return new ApiErrorResponse("failed", 4001, ex.getMessage());
    }

    @ExceptionHandler(value = {HttpMediaTypeNotAcceptableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse mediaTypeNotAccepted(Exception ex) {
        return new ApiErrorResponse("failed", 4003, ex.getMessage());
    }
       @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse unknownException(Exception ex) {
        return new ApiErrorResponse("failed", 500, ex.getMessage());
    }

}
