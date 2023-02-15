package sobinda.javadiplomcloud.advice;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sobinda.javadiplomcloud.error.ValidationErrorResponse;
import sobinda.javadiplomcloud.error.Violation;

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
}
