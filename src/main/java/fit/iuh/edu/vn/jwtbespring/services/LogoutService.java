package fit.iuh.edu.vn.jwtbespring.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) {
        String getcookie = "";
        if (request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                if (c.getName().equals("jwt")) {
                    getcookie = c.getValue();
                }
            }
        }
        if (getcookie == null) {
            return;
        } else {
            Cookie cookie = new Cookie("jwt", "");
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }

    }
}
