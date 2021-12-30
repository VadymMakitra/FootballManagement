package management.exception.handler;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Response object with information of occur management.exception.
 */
@Data
@NoArgsConstructor
public class ExceptionResponce {
    private String message;

    /**
     * Constructor with parameters.
     */
    public ExceptionResponce(Map<String, Object> errorAttributes) {
        this.setMessage((String) errorAttributes.get("message"));
    }
}