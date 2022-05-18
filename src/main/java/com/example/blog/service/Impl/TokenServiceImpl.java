package com.example.blog.service.Impl;


import com.example.blog.config.JwtTokenUtil;
import com.example.blog.entity.UserEntity;
import com.example.blog.model.request.JwtRequest;
import com.example.blog.model.request.LoginUserRequest;
import com.example.blog.model.response.JwtResponse;
import com.example.blog.service.TokenService;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {


    private JwtTokenUtil jwtTokenUtil;
    private UserDetailsService jwtInMemoryUserDetailsService;
    private UserService userService;
//    private AuthenticationManager authenticationManager;


    @Autowired
    public TokenServiceImpl(JwtTokenUtil jwtTokenUtil, UserDetailsService jwtInMemoryUserDetailsService,
                            UserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtInMemoryUserDetailsService = jwtInMemoryUserDetailsService;
        this.userService = userService;
    }
//    public TokenServiceImpl(JwtTokenUtil jwtTokenUtil, UserDetailsService jwtInMemoryUserDetailsService,
//                            UserService userService, AuthenticationManager authenticationManager) {
//        this.jwtTokenUtil = jwtTokenUtil;
//        this.jwtInMemoryUserDetailsService = jwtInMemoryUserDetailsService;
//        this.userService = userService;
//        this.authenticationManager = authenticationManager;
//    }


    @Override
    public JwtResponse generateToken(JwtRequest authenticationRequest) throws Exception {
//        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        LoginUserRequest userRequest = new LoginUserRequest();
        userRequest.setUsername(authenticationRequest.getUsername());
        userRequest.setPassword(authenticationRequest.getPassword());
        UserEntity account = userService.login(userRequest);
        if (account != null) {
            final UserDetails userDetails = jwtInMemoryUserDetailsService
                    .loadUserByUsername(authenticationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);
            UserEntity accountDTO = new UserEntity();
            accountDTO.setUserId(account.getUserId());
            accountDTO.setUsername(account.getUsername());
            accountDTO.setPassword(account.getPassword());
            accountDTO.setName(account.getName());
            accountDTO.setDob(account.getDob());
            accountDTO.setEmail(account.getEmail());
            accountDTO.setPhone(account.getPhone());
            accountDTO.setRole(account.getRole());
            return new JwtResponse(token, accountDTO);
        } else {
            throw new RuntimeException("Username or password incorrect");
        }
    }


//    private void authenticate(String username, String password) throws Exception {
//        Objects.requireNonNull(username);
//        Objects.requireNonNull(password);
//
//        try {
//            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException e) {
//            throw new Exception("USER_DISABLED", e);
//        } catch (BadCredentialsException e) {
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
//    }
}
