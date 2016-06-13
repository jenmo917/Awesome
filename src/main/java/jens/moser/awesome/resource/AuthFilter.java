package jens.moser.awesome.resource;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String appVersion = request.getHeader("app-version");
        LOG.info("appVersion: " + appVersion);
        LOG.info("URL = " + request.getRequestURL());

//        try {
//            int headerValue = Integer.parseInt(appVersion);
//            if (headerValue < 2) {
//                createErrorResponse(response);
//            }
//        } catch (NumberFormatException e) {
//            createErrorResponse(response);
//        }

        filterChain.doFilter(request, response);
    }

    private void createErrorResponse(HttpServletResponse response) throws IOException {
        response.sendError(412, "Wrong header");
    }
}