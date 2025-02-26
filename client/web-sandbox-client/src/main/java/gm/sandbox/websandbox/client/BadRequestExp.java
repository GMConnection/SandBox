package gm.sandbox.websandbox.client;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BadRequestExp extends RuntimeException{

    private final List<String> errors;

    public BadRequestExp(List<String> errors) {
        this.errors = errors;
    }

    public BadRequestExp(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public BadRequestExp(String message, Throwable cause, List<String> errors) {
        super(message, cause);
        this.errors = errors;
    }

    public BadRequestExp(Throwable cause, List<String> errors) {
        super(cause);
        this.errors = errors;
    }

    public BadRequestExp(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<String> errors) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errors = errors;
    }
}
