package org.launchcode.spaday.controllers;

import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("add")
    public String displayAddUserForm(Model model) {
        model.addAttribute(new User());
        return "user/add";
    }

    @PostMapping("add")
    public String processAddUserForm(Model model, @ModelAttribute User user, Errors errors, String verify) {
        if (user.getEmail() != null || user.getUsername() != null) {
            User userToRender = new User();
            userToRender.setEmail(user.getEmail());
            userToRender.setUsername(user.getUsername());
            model.addAttribute("user", userToRender);
        }

        if (errors.hasErrors() || !user.getPassword().equals(verify)) {
            model.addAttribute("error", "Passwords do not match");
            return "user/add";
        }
        model.addAttribute("hello", "Welcome " + user.getUsername() + "!");
        return "user/index";
    }
}
