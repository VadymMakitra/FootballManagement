package management.exception.handler;

import lombok.AllArgsConstructor;
import management.exception.CommandNotFoundException;
import management.exception.FootballerAlreadyInCommandException;
import management.exception.FootballerNotFoundException;
import management.exception.NotEnoughBudgetException;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private ErrorAttributes errorAttributes;

    private Map<String, Object> getErrorAttributes(WebRequest webRequest) {
        return new HashMap<>(errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.of(
                ErrorAttributeOptions.Include.MESSAGE)));
    }

    /**
     * Method for intercept {@link FootballerAlreadyInCommandException,NotEnoughBudgetException}.
     *
     * @param request contain info about thrown exception
     * @return {@link ResponseEntity} that contain http status and message of thrown exception
     */
    @ExceptionHandler({FootballerNotFoundException.class, CommandNotFoundException.class,})
    public final ResponseEntity<Object> handleNotFoundException(WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(getErrorAttributes(request));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    /**
     * Method for intercept {@link FootballerAlreadyInCommandException,NotEnoughBudgetException}.
     *
     * @param request contain info about thrown exception
     * @return {@link ResponseEntity} that contain http status and message of thrown exception
     */
    @ExceptionHandler({FootballerAlreadyInCommandException.class, NotEnoughBudgetException.class})
    public final ResponseEntity<Object> handleBadRequestException(WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(getErrorAttributes(request));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }
}
