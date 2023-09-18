
package snap.response;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /*
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        return ErrorResponse.toResponseEntity(ErrorStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getLocalizedMessage());
    }

     */

    @ExceptionHandler(SecurityException.class)
    protected ResponseEntity<ErrorResponse> handleException(SecurityException e) {
        return ErrorResponse.toResponseEntity(ErrorStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getLocalizedMessage());
    }

    @ExceptionHandler(MalformedJwtException.class)
    protected ResponseEntity<ErrorResponse> handleException(MalformedJwtException e) {
        return ErrorResponse.toResponseEntity(ErrorStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getLocalizedMessage());
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    protected ResponseEntity<ErrorResponse> handleException(UnsupportedJwtException e) {
        return ErrorResponse.toResponseEntity(ErrorStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getLocalizedMessage());
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    protected ResponseEntity<ErrorResponse> handleException(IllegalArgumentException e) {
        return ErrorResponse.toResponseEntity(ErrorStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getClass().getName());
    }

}