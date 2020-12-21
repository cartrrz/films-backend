package com.cartrrz.films.controller;

import com.cartrrz.films.dto.CredentialsDTO;
import com.cartrrz.films.dto.JwtDTO;
import com.cartrrz.films.dto.UserDTO;
import com.cartrrz.films.model.ObjectResponse;
import com.cartrrz.films.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    @ResponseBody
    public ObjectResponse signup(
            @RequestBody UserDTO userDTO
    ){
        ObjectResponse response = new ObjectResponse();
        try {
            userDTO = authService.signup(userDTO);
            response.setObject(userDTO);
            response.setSuccess(true);
        }catch (Exception e){
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    @PostMapping("/login")
    @ResponseBody
    public ObjectResponse login(
            @RequestBody CredentialsDTO credentialsDTO
    ){
        ObjectResponse response = new ObjectResponse();
        try {
            JwtDTO dto = authService.login(credentialsDTO);
            response.setObject(dto);
            response.setSuccess(true);
        }catch (Exception e){
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }
        return response;
    }
}
