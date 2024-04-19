package com.example.testjwt.controllers;

import com.example.testjwt.dto.request.SignUpForm;
import com.example.testjwt.dto.response.ResponseMessage;
import com.example.testjwt.dto.response.ResponseObject;
import com.example.testjwt.model.Role;
import com.example.testjwt.model.RoleName;
import com.example.testjwt.model.User;
import com.example.testjwt.repositories.IUserRepository;
import com.example.testjwt.service.IUserService;
import com.example.testjwt.service.implement.RoleServiceImpl;
import com.example.testjwt.service.implement.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    IUserRepository userRepository;

    @GetMapping("")
    List<User> users () {
        return userService.findByUsername("").stream().toList();
    }

//    @PostMapping("/register")
//    public ResponseEntity<ResponseMessage> register(@Valid @RequestBody SignUpForm signUpForm) {
//        System.out.println(signUpForm.getUsername());
//        if (userService.existsByUsername(signUpForm.getUsername())) {
//            return new ResponseEntity<>(new ResponseMessage("The username existed!"), HttpStatus.NOT_IMPLEMENTED);
//        }
//        if (userService.existsByEmail(signUpForm.getEmail())) {
//            return new ResponseEntity<>(new ResponseMessage("The email existed!"), HttpStatus.OK);
//        }
//        User user = new User(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(), passwordEncoder.encode(signUpForm.getPassword()));
//        Set<String> strRoles = signUpForm.getRoles();
//        Set<Role> roles = new HashSet<>();
//        strRoles.forEach(role -> {
//            switch (role) {
//                case "admin":
//                    Role adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow(
//                            () -> new RuntimeException("Role not found")
//                    );
//                    roles.add(adminRole);
//                    break;
//                case "user":
//                    Role userRole = roleService.findByName(RoleName.USER).orElseThrow(
//                            () -> new RuntimeException("Role not found")
//                    );
//                    roles.add(userRole);
//                    break;
//            }
//        });
//        user.setRoles(roles);
//        userService.save(user);
//        return new ResponseEntity<>(new ResponseMessage("Create user success!"), HttpStatus.OK);
//    }
    @PostMapping("/register")
public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm){
    if(userService.existsByUsername(signUpForm.getUsername())){
        return new ResponseEntity<>(new ResponseMessage("this username has been used"), HttpStatus.OK);
    }
    if(userService.existsByEmail(signUpForm.getEmail())){
        return new ResponseEntity<>(new ResponseMessage("this email has been used"), HttpStatus.OK);
    }
    User user = new User(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(), passwordEncoder.encode(signUpForm.getPassword()));
    Set<String> strRoles = signUpForm.getRoles();
    Set<Role> roles = new HashSet<>();
//    strRoles.forEach(role ->{
//        switch (role){
//            case "admin":
//                Role adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow(()->
//                        new RuntimeException("Role NOT FOUND"));
//                roles.add(adminRole);
//                break;
//
//            default: Role userRole = roleService.findByName(RoleName.USER).orElseThrow(()->new RuntimeException("ROLE NOT FOUND"));
//                roles.add(userRole);
//        }
//    });
    user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new ResponseMessage("create success!!!"), HttpStatus.OK);
}
//    @PostMapping("/signin")
//    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String token = jwtProvider.createToken(authentication);
//        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
//        return ResponseEntity.ok(new JwtResponse(token,userPrinciple.getName(), userPrinciple.getAuthorities()));
//
//    }
}