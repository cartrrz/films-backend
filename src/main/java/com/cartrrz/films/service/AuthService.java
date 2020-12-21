package com.cartrrz.films.service;

import com.cartrrz.films.dto.CredentialsDTO;
import com.cartrrz.films.dto.JwtDTO;
import com.cartrrz.films.dto.UserDTO;

public interface AuthService {

    UserDTO signup(UserDTO userDTO) throws Exception;

    JwtDTO login(CredentialsDTO credentialsDTO);
}
