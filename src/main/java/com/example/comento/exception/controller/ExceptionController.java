package com.example.comento.exception.controller;

import com.example.comento.dto.ErrorResponseDto;
import com.example.comento.exception.CustomException;
import com.example.comento.exception.DtoValidationException;
import com.example.comento.exception.errorcode.ErrorCode;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class ExceptionController {

    @ExceptionHandler({CustomException.class})
    protected ResponseEntity<ErrorResponseDto> handleCustomException(CustomException exception){
        writeLog(exception);
        HttpStatus httpStatus = resolveHttpStatus(exception);
        return new ResponseEntity<>(ErrorResponseDto.res(exception), httpStatus);
    }

    @ExceptionHandler({ValidationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponseDto> handleCustomException(MethodArgumentNotValidException methodArgumentNotValidException){
        FieldError fieldError = methodArgumentNotValidException.getBindingResult().getFieldError();
        if(fieldError == null){
            return new ResponseEntity<>(ErrorResponseDto.res(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    methodArgumentNotValidException), HttpStatus.BAD_REQUEST);
        }
        ErrorCode validationErrorCode = ErrorCode.resolveValidationErrorCode(fieldError.getCode());
        String detail = fieldError.getDefaultMessage();
        DtoValidationException dtoValidationException = new DtoValidationException(validationErrorCode, detail);
        writeLog(dtoValidationException);
        return new ResponseEntity<>(ErrorResponseDto.res(dtoValidationException),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException){
        writeLog(entityNotFoundException);
        return new ResponseEntity<>(ErrorResponseDto.res(String.valueOf(HttpStatus.NOT_FOUND.value()),entityNotFoundException), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception e){
        writeLog(e);
        return new ResponseEntity<>(
                ErrorResponseDto.res(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }


    private void writeLog(CustomException customException){
        String nameOfException = customException.getClass().getSimpleName();
        String messageOfException = customException.getErrorCode().getMessage();
        String detailOfException = customException.getDetail();
        log.error("[{}]{}:{}", nameOfException, messageOfException, detailOfException);
    }

    private void writeLog(Exception e){
        String nameOfException = e.getClass().getSimpleName();
        String messageOfException = e.getMessage();
        log.error("[]: {}", nameOfException, messageOfException);
    }

    private HttpStatus resolveHttpStatus(CustomException exception){
        return HttpStatus.resolve(Integer.parseInt(exception.getErrorCode().getStatus().substring(0,3)));
    }

}
