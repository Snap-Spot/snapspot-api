package snap.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws IOException {
        try{
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e){
            throwException(response, ResponseMessage.JWT_EXPIRE, e);
        } catch (JwtException | IllegalArgumentException | ServletException e){
            throwException(response, ResponseMessage.JWT_INVALID, e);
        }
    }

    private void throwException(
            HttpServletResponse response,
            String message,
            Exception e
    ) {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(ErrorStatus.INTERNAL_SERVER_ERROR.getStatusCode());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ErrorResponse errorResponse = new ErrorResponse(ErrorStatus.INTERNAL_SERVER_ERROR, message, e.getMessage(), e.getLocalizedMessage());
        try{
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        } catch (IOException el){
            el.printStackTrace();
        }
    }

}