package kvadrakopter3.super_project.Filters;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class csrfFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
//        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");

//        response.addCookie(new Cookie("XSRF-TOKEN",csrfToken.getToken()));
//
//        response.setHeader("XSRF-TOKEN", csrfToken.getToken());

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Credentials", String.valueOf(true));
        response.setHeader("Access-Control-Allow-Methods", "*");

        filterChain.doFilter(request,response);
    }
}
