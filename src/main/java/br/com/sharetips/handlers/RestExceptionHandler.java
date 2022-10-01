package br.com.sharetips.handlers;

import br.com.sharetips.exceptions.BadRequestException;
import br.com.sharetips.exceptions.BadRequestExceptionDetails;
import br.com.sharetips.exceptions.ExceptionDetails;
import br.com.sharetips.exceptions.ValidationExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException exception) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails
                        .builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, check the documentation.")
                        .details(exception.getMessage())
                        .developerMessage(exception.getClass().getName())
                        .build()
                , HttpStatus.BAD_REQUEST
        );
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<String> fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.toList());
        List<String> fieldsMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        return new ResponseEntity<>(
                ValidationExceptionDetails
                        .builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, invalid fields.")
                        .details("Check the field(s) error")
                        .developerMessage(exception.getClass().getName())
                        .fields(fields)
                        .fieldsMessages(fieldsMessages)
                        .build()
                , HttpStatus.BAD_REQUEST
        );
    }

    // Default internal exceptions handler
    @Override
    protected  ResponseEntity<Object> handleExceptionInternal(
            Exception exception, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        ExceptionDetails exceptionDetails = ExceptionDetails
                .builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .title(exception.getCause().getMessage())
                .details(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();

        return new ResponseEntity<>(exceptionDetails, headers, status);
    }
}
