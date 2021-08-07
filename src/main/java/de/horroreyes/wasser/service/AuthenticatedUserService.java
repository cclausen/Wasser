package de.horroreyes.wasser.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.horroreyes.wasser.model.User;
import de.horroreyes.wasser.repositories.UserRepository;

@Service
public class AuthenticatedUserService implements UserDetailsService {
    private final UserRepository userRepository;

    public AuthenticatedUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseGet(() -> userRepository.findByEmail(username).orElse(null));
        if (user == null) {
            throw new UsernameNotFoundException("The user " + username + " does not exist");
        }
        return new AuthenticatedUser(user);
    }
}
