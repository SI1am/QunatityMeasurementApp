package com.app.quantitymeasurement.config;

import com.app.quantitymeasurement.model.User;
import com.app.quantitymeasurement.repository.UserRepository;
import com.app.quantitymeasurement.service.JwtService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public OAuth2AuthenticationSuccessHandler(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        String provider = token.getAuthorizedClientRegistrationId();
        String email = token.getPrincipal().getAttributes().get("email") != null ? 
                token.getPrincipal().getAttributes().get("email").toString() : 
                token.getPrincipal().getAttributes().get("sub").toString() + "@" + provider + ".oauth";
        String name = token.getPrincipal().getAttributes().get("name") != null ? 
                token.getPrincipal().getAttributes().get("name").toString() : email;

        User user = userRepository.findByEmail(email).orElseGet(() -> {
            User newUser = new User(email, "", name, provider);
            return userRepository.save(newUser);
        });

        if (user.getName() == null || user.getName().isEmpty()) {
            user.setName(name);
            userRepository.save(user);
        }

        String jwt = jwtService.generateToken(user.getEmail());

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(String.format(
                "{\"token\":\"%s\",\"type\":\"Bearer\",\"email\":\"%s\",\"name\":\"%s\",\"provider\":\"%s\"}",
                jwt, user.getEmail(), user.getName(), user.getProvider()
        ));
    }
}
