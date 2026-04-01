package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.dto.AuthResponse;
import com.app.quantitymeasurement.dto.LoginRequest;
import com.app.quantitymeasurement.dto.SignupRequest;
import com.app.quantitymeasurement.service.AuthService;
import com.app.quantitymeasurement.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "Authentication APIs")
public class AuthController {
    
    private final AuthService authService;
    private final JwtService jwtService;
    
    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }
    
    @PostMapping("/signup")
    @Operation(summary = "Register a new user")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest request) {
        try {
            AuthResponse response = authService.signup(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @PostMapping("/login")
    @Operation(summary = "Login with email and password")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @PostMapping("/google")
    @Operation(summary = "Authenticate with Google token")
    public ResponseEntity<?> googleAuth(@RequestBody Map<String, String> request) {
        try {
            String token = request.get("token");
            if (token == null || token.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Token is required"));
            }
            
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid token format"));
            }
            
            String payload = new String(Base64.getUrlDecoder().decode(parts[1]));
            
            String email = extractFromJson(payload, "email");
            String name = extractFromJson(payload, "name");
            String picture = extractFromJson(payload, "picture");
            
            if (email == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "Email not found in token"));
            }
            
            AuthResponse response = authService.googleAuth(email, name, picture);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid Google token: " + e.getMessage()));
        }
    }
    
    private String extractFromJson(String json, String key) {
        int keyIndex = json.indexOf("\"" + key + "\"");
        if (keyIndex == -1) return null;
        
        int colonIndex = json.indexOf(":", keyIndex);
        int valueStart = json.indexOf("\"", colonIndex);
        int valueEnd = json.indexOf("\"", valueStart + 1);
        
        if (valueStart == -1 || valueEnd == -1) return null;
        return json.substring(valueStart + 1, valueEnd);
    }
    
    @GetMapping("/validate")
    @Operation(summary = "Validate JWT token")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authHeader) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.badRequest().body(Map.of("valid", false, "error", "Missing or invalid Authorization header"));
            }
            
            String token = authHeader.substring(7);
            String email = jwtService.extractEmail(token);
            
            if (jwtService.isTokenValid(token)) {
                return ResponseEntity.ok(Map.of("valid", true, "email", email));
            } else {
                return ResponseEntity.ok(Map.of("valid", false, "error", "Token expired or invalid"));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(Map.of("valid", false, "error", e.getMessage()));
        }
    }
}
