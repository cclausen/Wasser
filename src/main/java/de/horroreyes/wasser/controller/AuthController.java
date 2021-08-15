package de.horroreyes.wasser.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.horroreyes.wasser.model.Role;
import de.horroreyes.wasser.model.Roles;
import de.horroreyes.wasser.model.User;
import de.horroreyes.wasser.repositories.RoleRepository;
import de.horroreyes.wasser.repositories.UserRepository;
import de.horroreyes.wasser.security.AuthenticatedUser;
import de.horroreyes.wasser.security.jwt.JwtUtils;
import de.horroreyes.wasser.security.payload.JwtResponse;
import de.horroreyes.wasser.security.payload.LoginRequest;
import de.horroreyes.wasser.security.payload.MessageResponse;
import de.horroreyes.wasser.security.payload.SignupRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    final AuthenticationManager authenticationManager;

    final UserRepository userRepository;

    final RoleRepository roleRepository;

    final PasswordEncoder encoder;

    final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
            RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        AuthenticatedUser userDetails = (AuthenticatedUser) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.ok().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(Roles.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Default-Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                case "admin" -> {
                    Role adminRole = roleRepository.findByName(Roles.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Admin-Role is not found."));
                    roles.add(adminRole);
                }
                case "mod" -> {
                    Role modRole = roleRepository.findByName(Roles.ROLE_MODERATOR)
                            .orElseThrow(() -> new RuntimeException("Error: Moderator-Role is not found."));
                    roles.add(modRole);
                }
                default -> {
                    Role userRole = roleRepository.findByName(Roles.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: User-Role is not found."));
                    roles.add(userRole);
                }
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok().body(new MessageResponse("User registered successfully!"));
    }
}
