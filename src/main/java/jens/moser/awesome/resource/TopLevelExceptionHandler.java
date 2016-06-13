package jens.moser.awesome.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import jens.moser.awesome.domain.exception.NotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class TopLevelExceptionHandler {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AuthFilter.class);

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFound(HttpServletRequest request,
                               HttpServletResponse response,
                               Exception exception) throws Exception {

        LOG.error("TopLevel", exception);
        createErrorResponseBody(response, exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleConflict(HttpServletRequest request,
                               HttpServletResponse response,
                               Exception exception) throws Exception {

        LOG.error("TopLevel", exception);
        createErrorResponseBody(response, exception.getMessage());
    }

    private void createErrorResponseBody(HttpServletResponse response, String message) throws IOException{

        Error error = new Error(message);
        ObjectMapper objectMapper = new ObjectMapper();

        response.addHeader("Content-type","application/json; charset=utf-8");
        response.getWriter().println(objectMapper.writeValueAsString(error));
    }
}

class Error {

    private String errorMessage;

    Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
