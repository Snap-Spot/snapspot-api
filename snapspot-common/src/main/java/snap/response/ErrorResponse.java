package snap.response;

import io.micrometer.core.ipc.http.HttpSender;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private int status;
    private String error;
    private String code;
    private String message;
    private String details;


    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorStatus errorCode, String details, String message) {
        return ResponseEntity
                .status(errorCode.getStatusCode())
                /*
                .body(ErrorResponse.builder()
                        .code(errorCode.getCode())
                        .details(details)
                        .message(message)
                        .build()
                );

                 */
                .body(new ErrorResponse(errorCode, details, message, message));
    }

    public static ResponseEntity<Object> toResponseEntityObject(ErrorStatus errorCode, String details, String message) {
        return ResponseEntity
                .status(errorCode.getStatusCode())
                /*
                .body(ErrorResponse.builder()
                        .code(errorCode.getCode())
                        .details(details)
                        .message(message)
                        .build()
                );

                 */
                .body(new ErrorResponse(errorCode, details, message, message));
    }


    public ErrorResponse(ErrorStatus errorCode, String details, String message, String error) {
        this.status = errorCode.getStatusCode();
        this.error = error;
        this.code = errorCode.getCode();
        this.message = message;
        this.details = details;
    }
}
