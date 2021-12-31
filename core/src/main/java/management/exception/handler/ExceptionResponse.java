package management.exception.handler;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Response object with information of occur management.exception.
 */
@Data
@NoArgsConstructor
public class ExceptionResponse {
    private String message;

    /**
     * Constructor with parameters.
     */
    public ExceptionResponse(Map<String, Object> errorAttributes) {
        this.setMessage((String) errorAttributes.get("message"));
    }
}