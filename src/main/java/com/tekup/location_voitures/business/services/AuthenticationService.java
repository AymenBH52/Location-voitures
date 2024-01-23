package com.tekup.location_voitures.business.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tekup.location_voitures.Web.models.requests.UserForm;
import com.tekup.location_voitures.dao.entities.AuthenticationRequest;
import com.tekup.location_voitures.dao.entities.AuthenticationResponse;
import com.tekup.location_voitures.dao.entities.User;
import com.tekup.location_voitures.dao.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AuthenticationService {
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthenticationResponse register(UserForm userForm) {
                var user = User.builder()
                                .firstName(userForm.getFirstName())
                                .lastName(userForm.getLastName())
                                .username(userForm.getUsername())
                                .password(passwordEncoder.encode(userForm.getPassword()))
                                .build();
                userRepository.save(user);
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .token(jwtToken)

                                .build();
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getUsername(),
                                                request.getPassword()));
                var user = userRepository.findByUsername(request.getUsername())
                                .orElseThrow();
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .token(jwtToken)

                                .build();
        }

}
