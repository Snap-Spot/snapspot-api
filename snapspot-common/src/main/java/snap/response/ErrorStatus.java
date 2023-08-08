package snap.response;

import lombok.Getter;

@Getter
public enum ErrorStatus {
    OK(200, "OK"),
    CREATED(201, "CREATED"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    NOT_FOUND(404, "NOT_FOUND"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR");

    int statusCode;
    String code;

    ErrorStatus(int statusCode, String code) {
        this.statusCode = statusCode;
        this.code = code;
    }
}
