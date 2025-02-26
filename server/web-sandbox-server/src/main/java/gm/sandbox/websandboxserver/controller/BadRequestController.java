package gm.sandbox.websandboxserver.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class BadRequestController {

    private final MessageSource messageSource;

    public BadRequestController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ProblemDetail> handleException(BindException e, Locale locale) {
        ProblemDetail problem = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST,
                        messageSource.getMessage("errors.400.title", new Object[0], locale));

        problem.setProperty("errors", e.getAllErrors().stream().map(ObjectError::getDefaultMessage).toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }


}
