package hr.abysalto.hiring.mid.service;

import hr.abysalto.hiring.mid.configuration.JwtProperties;
import hr.abysalto.hiring.mid.domain.User;
import hr.abysalto.hiring.mid.dto.auth.AuthResponse;
import hr.abysalto.hiring.mid.dto.auth.LoginRequest;
import hr.abysalto.hiring.mid.dto.auth.RegisterRequest;
import hr.abysalto.hiring.mid.dto.auth.UserResponse;
import hr.abysalto.hiring.mid.exception.BadRequestException;
import hr.abysalto.hiring.mid.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtEncoder jwtEncoder;
    private final JwtProperties jwtProps;

    @Transactional
    public UserResponse register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.email())) {
            throw new BadRequestException("Email is already in use.");
        }

        User user = User.builder()
                .email(req.email())
                .password(passwordEncoder.encode(req.password()))
                .firstName(req.firstName().trim())
                .lastName(req.lastName().trim())
                .build();

        User saved = userRepository.save(user);

        return new UserResponse(saved.getId(), saved.getEmail(), saved.getFirstName(), saved.getLastName());
    }

    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest req) {
        String email = req.email();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        if (!passwordEncoder.matches(req.password(), user.getPassword())) {
            throw new BadRequestException("Invalid email or password");
        }

        Instant now = Instant.now();
        Instant exp = now.plusSeconds(jwtProps.accessTokenTtlMinutes() * 60);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(jwtProps.issuer())
                .issuedAt(now)
                .expiresAt(exp)
                .subject(String.valueOf(user.getId()))
                .claim("email", user.getEmail())
                .build();
        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).type("JWT").build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
        return new AuthResponse(token);
    }

    public UserResponse me() {
        Long userId = id();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("User not found"));

        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    public static Long id() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null) {
            throw new BadRequestException("Unauthenticated");
        }

        try {
            return Long.parseLong(auth.getPrincipal().toString());
        } catch (NumberFormatException ex) {
            throw new BadRequestException("Invalid authentication principal");
        }
    }
}