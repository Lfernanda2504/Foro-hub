package com.aluracurso.forohub.controller;
import com.aluracurso.forohub.domain.users.DataUserAuth;
import com.aluracurso.forohub.domain.users.User;
import com.aluracurso.forohub.security.DatosJWTToken;
import com.aluracurso.forohub.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity userAuthentication(@RequestBody @Valid DataUserAuth dataUserAuth) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(dataUserAuth.login(), dataUserAuth.password());
        var authenticatedUser = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generateToken((User) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

}
