package varad.in.spring_boot_jwt_authentication_api.service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import varad.in.spring_boot_jwt_authentication_api.dto.LoginRequest;
import varad.in.spring_boot_jwt_authentication_api.dto.RegisterRequest;
import varad.in.spring_boot_jwt_authentication_api.entity.User;
import varad.in.spring_boot_jwt_authentication_api.repository.UserRepository;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }
    public void register(RegisterRequest request)
    {
        if(userRepository.existsByEmail(request.getEmail()))
        {
            throw new RuntimeException("enail already registered");
        }

    User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        String encodedPassword =
                passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
}
    public void login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
    }
}
