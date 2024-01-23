package com.tekup.location_voitures.Web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tekup.location_voitures.Web.models.requests.UserForm;
import com.tekup.location_voitures.business.services.AuthenticationService;
import com.tekup.location_voitures.dao.entities.AuthenticationRequest;
import com.tekup.location_voitures.dao.entities.AuthenticationResponse;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userForm") UserForm userForm, HttpSession session) {
        AuthenticationResponse registrationResponse = service.register(userForm);
        session.setAttribute("token", registrationResponse.getToken());
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("authenticationRequest", new AuthenticationRequest());
        return "login";

    }

    @PostMapping("/login")
    public String authenticate(@ModelAttribute("authenticationRequest") AuthenticationRequest request,
            HttpSession session) {
        AuthenticationResponse authenticationResponse = service.authenticate(request);
        session.setAttribute("token", authenticationResponse.getToken());
        return "redirect:/Admin";
    }
}
