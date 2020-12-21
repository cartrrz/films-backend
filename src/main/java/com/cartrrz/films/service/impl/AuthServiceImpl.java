package com.cartrrz.films.service.impl;

import com.cartrrz.films.dto.CredentialsDTO;
import com.cartrrz.films.dto.JwtDTO;
import com.cartrrz.films.dto.UserDTO;
import com.cartrrz.films.model.sql.Role;
import com.cartrrz.films.model.sql.User;
import com.cartrrz.films.model.sql.UserDetail;
import com.cartrrz.films.repository.RoleRepository;
import com.cartrrz.films.repository.UserRepository;
import com.cartrrz.films.util.jwt.JwtUtils;
import com.cartrrz.films.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Override
    public UserDTO signup(UserDTO userDTO) throws Exception {
        if(userDTO == null) throw new Exception("user is null");

        if(userRepository.existsByUsername(userDTO.getUsername())){
            logger.error("username already exist");
            throw new Exception("username already exist");
        }

        Role role = roleRepository.findByRoleKey("CUSTOMER").
                orElseThrow(() -> new Exception(" role not found"));

        UserDetail userDetail = new UserDetail();
        userDetail.setAddress(userDTO.getAddress());
        userDetail.setEmail(userDTO.getEmail());
        userDetail.setFirstName(userDTO.getFirstName());
        userDetail.setLastName(userDTO.getLastName());
        userDetail.setCreatedDate(LocalDateTime.now());
        userDetail.setModifiedDate(LocalDateTime.now());

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setUserDetail(userDetail);
        user.setRole(role);
        user.setCreatedDate(LocalDateTime.now());
        user.setModifiedDate(LocalDateTime.now());
        logger.info("user: {}", user);
        userRepository.save(user);

        return userDTO;
    }

    @Override
    public JwtDTO login(CredentialsDTO credentialsDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentialsDTO.getUsername(), credentialsDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        JwtDTO dto = new JwtDTO();
        dto.setJwt(jwt);
        dto.setUserId(userDetails.getId());
        dto.setUsername(userDetails.getUsername());

        return dto;
    }


}
