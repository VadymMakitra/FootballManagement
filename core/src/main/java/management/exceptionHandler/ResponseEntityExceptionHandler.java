package management.exceptionHandler;

import exception.FootballerNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestControllerAdvice
public class ResponseEntityExceptionHandler extends org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler {
    private ErrorAttributes errorAttributes;

    private Map<String, Object> getErrorAttributes(WebRequest webRequest) {
        return new HashMap<>(errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.of(
                ErrorAttributeOptions.Include.MESSAGE)));
    }

    /**
     * Method for intercept {@link FootballerNotFoundException}.
     *
     * @param request contain info about thrown exception
     * @return {@link ResponseEntity} that contain http status and message of thrown exception.
     */
    @ExceptionHandler({FootballerNotFoundException.class})
    public final ResponseEntity<Object> handleCertificateNotFound(WebRequest request) {
        ExceptionResponce exceptionResponse = new ExceptionResponce(getErrorAttributes(request));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
}
