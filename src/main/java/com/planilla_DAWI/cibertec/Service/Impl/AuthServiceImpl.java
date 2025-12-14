package com.planilla_DAWI.cibertec.Service.Impl;

import com.planilla_DAWI.cibertec.Dto.JwtResponseDto;
import com.planilla_DAWI.cibertec.Dto.LoginRequestDto;
import com.planilla_DAWI.cibertec.Dto.RegisterRequestDto;
import com.planilla_DAWI.cibertec.Dto.RegisterResponseDto;
import com.planilla_DAWI.cibertec.Entity.Usuario;
import com.planilla_DAWI.cibertec.Repository.UsuarioRepository;
import com.planilla_DAWI.cibertec.Service.AuthService;
import com.planilla_DAWI.cibertec.Utils.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.planilla_DAWI.cibertec.Utils.Helps.Convert.isValidEmail;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtUtil jwtUtil,
                           UserDetailsService userDetailsService,
                           UsuarioRepository usuarioRepository,
                           PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public JwtResponseDto authenticateUser(LoginRequestDto loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return new JwtResponseDto(jwt);
    }
    @Override
    public RegisterResponseDto registerUser(RegisterRequestDto registerRequest) {
        // Validar que el username no exista
        if (usuarioRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }

        // Validar que el email no exista
        if (usuarioRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        // Validar que el rol no sea nulo
        if (registerRequest.getRole() == null) {
            throw new RuntimeException("El rol es obligatorio");
        }

        // Validar formato de email
        if (!isValidEmail(registerRequest.getEmail())) {
            throw new RuntimeException("El formato del email no es válido");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(registerRequest.getUsername());
        usuario.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        usuario.setEmail(registerRequest.getEmail());
        usuario.setRole(registerRequest.getRole());
        usuario.setEnabled(true);

        usuarioRepository.save(usuario);

        return new RegisterResponseDto(
                true,
                String.format("Usuario %s registrado exitosamente como %s",
                        registerRequest.getUsername(),
                        registerRequest.getRole().getDescription()),
                registerRequest.getUsername(),
                registerRequest.getRole().name()
        );
    }
}
