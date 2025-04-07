package org.grandmasfood.springcloud.users.infrastructure.adapters.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.grandmasfood.springcloud.users.application.services.TokenServices;
import org.grandmasfood.springcloud.users.infrastructure.adapters.output.mapper.UserMapper;
import org.grandmasfood.springcloud.users.infrastructure.adapters.output.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenServices tokenServices;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public JwtAuthenticationFilter(TokenServices tokenServices, UserRepository userRepository, UserMapper userMapper) {
        this.tokenServices = tokenServices;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var token = request.getHeader("Authorization");

        if (token != null) {
            System.out.println("Notnull: " + token);
            token = token.replace("Bearer ", "");
            var subject = tokenServices.getSubject(token);

            if (subject != null) {
                var user = userMapper.toUser(userRepository.findByEmail(subject));
                var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }
}