package com.persona.bmod.core.controllers;

import com.persona.bmod.core.models.DomainConstants;
import com.persona.bmod.core.models.Profile;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("web")
@Slf4j
public class HTMLController {

    @ModelAttribute
    public void setCtx(Model model, HttpServletRequest request) {
        String path = request.getRequestURI().split("\\?")[0];
        String view = path.substring(path.lastIndexOf("/") + 1);
        model.addAttribute(DomainConstants.CONTENT_VIEW, view);
    }

    @GetMapping("login")
    public String getLoginForm(Model model) {
        model.addAttribute("profile", new Profile());
        return "layout";
    }

    @GetMapping("profile")
    public String getProfile() {
        return "layout";
    }

    @PostMapping("register")
    public String register(@ModelAttribute Profile profile) {
        log.debug("registering profile: {}", profile.getName());
        return "forward://profile";
    }

    @PostMapping("login")
    public String login() {
        return "forward://profile";
    }

    @RequestMapping("logout")
    public String logout() {
        return "redirect:/";
    }

    @PostMapping("profile")
    public String saveProfile() {
        return "forward://profile";
    }
}
