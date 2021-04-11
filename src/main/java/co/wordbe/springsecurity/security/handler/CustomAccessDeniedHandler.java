package co.wordbe.springsecurity.security.handler;

import lombok.Setter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Setter
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private String errorPage;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        String encodedException = URLEncoder.encode(accessDeniedException.getMessage(), "UTF-8");
        String deniedUrl = errorPage + "?exception=" + encodedException;
        response.sendRedirect(deniedUrl);
    }
}
