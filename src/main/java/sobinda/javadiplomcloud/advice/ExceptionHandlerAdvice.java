package sobinda.javadiplomcloud.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sobinda.javadiplomcloud.error.ValidationErrorResponse;
import sobinda.javadiplomcloud.error.Violation;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onBindException(BindException e) {
        final List<Violation> violations = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(
                        x -> new Violation(
                                x.getField(),
                                x.getDefaultMessage()
                        )
                )
                .collect(Collectors.toList());
        System.out.printf("[onBindExceptionviolations]:%s\n", violations);
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<String> onFileNotFoundException(FileNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(FileAlreadyExistsException.class)
    public ResponseEntity<String> onFileAlreadyExistsException(FileAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
