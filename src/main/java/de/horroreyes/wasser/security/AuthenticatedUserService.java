package de.horroreyes.wasser.security;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.horroreyes.wasser.model.User;
import de.horroreyes.wasser.repositories.UserRepository;

/**
 * Get the user by
 * <code>
 * AuthenticatedUser userDetails =
 * (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 * <p>
 * // userDetails.getUsername()
 * // userDetails.getPassword()
 * // userDetails.getAuthorities()
 * </code>
 */
@Service
public class AuthenticatedUserService implements UserDetailsService {
    private final UserRepository userRepository;

    public AuthenticatedUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) {
        User user = userRepository.findByUsernameOrEmail(login, login)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "The user " + login + " does not exist (as username or email)"));
        return AuthenticatedUser.build(user);
    }
}
