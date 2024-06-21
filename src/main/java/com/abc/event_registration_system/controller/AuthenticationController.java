package com.abc.event_registration_system.controller;

import com.abc.event_registration_system.dto.request.LoginDTO;
import com.abc.event_registration_system.dto.request.PasswordChangerDTO;
import com.abc.event_registration_system.dto.response.UserDTO;
import com.abc.event_registration_system.dto.response.UserTokenDTO;
import com.abc.event_registration_system.model.User;
import com.abc.event_registration_system.security.TokenUtils;
import com.abc.event_registration_system.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;


    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody @Valid LoginDTO authenticationRequest) {
        return new ResponseEntity<>(userDetailsService.login(authenticationRequest), HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<UserTokenDTO> refreshAuthenticationToken(HttpServletRequest request) {
        return new ResponseEntity<>(userDetailsService.refreshAuthenticationToken(request), HttpStatus.OK);
    }

    @PostMapping("/createuser")
    public ResponseEntity<User> createuser(@RequestBody User user) throws Exception {
        return new ResponseEntity<>(userDetailsService.createuser(user), HttpStatus.OK);

    }
    @PostMapping("/change-password")
    public ResponseEntity changePassword(@RequestBody @Valid PasswordChangerDTO passwordChanger) {
        userDetailsService.changePassword(passwordChanger.getOldPassword(), passwordChanger.getNewPassword());
        return ResponseEntity.ok().build();
    }

}

