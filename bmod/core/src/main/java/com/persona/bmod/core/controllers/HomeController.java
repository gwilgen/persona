package com.persona.bmod.core.controllers;

import com.persona.bmod.core.models.DomainConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {

    @RequestMapping("")
    public String home(Model model) {
        model.addAttribute(DomainConstants.CONTENT_VIEW, "landing");
        return "layout.html";
    }
}
