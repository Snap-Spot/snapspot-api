package snap.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SuccessResponse {
    private final int status;
    private final String code;
    private final String message;
    private final String details;
}
