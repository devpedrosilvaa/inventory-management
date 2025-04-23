package tech.silva.inventory.modules.auth.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final UserDetailsService detailsService;

    public JwtAuthorizationFilter(UserDetailsService detailsService) {
        this.detailsService = detailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = request.getHeader(JwtUtils.JWT_AUTHORIZATION);
        if(token == null || !token.startsWith(JwtUtils.JWT_BEARER)){
            filterChain.doFilter(request, response);
            return;
        }

        if(!JwtUtils.isTokenValid(token)){
            filterChain.doFilter(request, response);
            return;
        }

        String username = JwtUtils.getUsernameFromToken(token);
        if (SecurityContextHolder.getContext().getAuthentication() == null)
            authenticateUserFromToken(request, username);
        filterChain.doFilter(request, response);
    }

    private void authenticateUserFromToken(HttpServletRequest request, String username) {
        UserDetails userDetails = detailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
