package com.enigma.wmb.controller;

import com.enigma.wmb.dto.response.CommonResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<CommonResponse<?>> responseStatusHandler(ResponseStatusException e) {
        CommonResponse<Object> response = CommonResponse.builder()
                .statusCode(e.getStatusCode().value())
                .message(e.getReason())
                .build();
        return ResponseEntity.status(e.getStatusCode())
                .body(response);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<CommonResponse<?>> constraintViolationException(ConstraintViolationException e){
        CommonResponse<Object> response = CommonResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
    @ExceptionHandler({InvalidDataAccessApiUsageException.class})
    public ResponseEntity<CommonResponse<?>> invalidDataAccess(InvalidDataAccessApiUsageException e){
        CommonResponse<Object> response = CommonResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler({PropertyReferenceException.class})
    public ResponseEntity<CommonResponse<?>> propertyReferenceException(PropertyReferenceException e){
        CommonResponse<Object> response = CommonResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<CommonResponse<?>> illegalArgument(IllegalArgumentException e){
        CommonResponse<Object> response = CommonResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<CommonResponse<?>> dataIntegrityHandler(DataIntegrityViolationException e){
        CommonResponse.CommonResponseBuilder<Object> builder = CommonResponse.builder();
        HttpStatus httpStatus;

        if (e.getMessage().contains("foreign key constraint")){
            builder.statusCode(HttpStatus.BAD_REQUEST.value());
            builder.message("tidak dapat menghapus data karena ada referensi dari tabel lain");
            httpStatus = HttpStatus.BAD_REQUEST;
        } else if (e.getMessage().contains("unique constraint") || e.getMessage().contains("Duplicate entry")) {
            builder.statusCode(HttpStatus.CONFLICT.value());
            builder.message("Data duplikat");
            httpStatus = HttpStatus.CONFLICT;
        }else {
            builder.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            builder.message("internal server error");
            httpStatus = HttpStatus.INSUFFICIENT_STORAGE;
        }
        return ResponseEntity.status(httpStatus).body(builder.build());
    }
}
