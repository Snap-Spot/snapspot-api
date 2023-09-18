package snap.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ErrorResponse {
    private final int status;
    private final String error;
    private final String code;
    private final String message;
    private final String details;


    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorStatus errorCode, String details, String message) {
        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(ErrorResponse.builder()
                        .code(errorCode.getCode())
                        .details(details)
                        .message(message)
                        .build()
                );
    }
}
