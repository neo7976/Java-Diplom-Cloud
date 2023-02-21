package sobinda.javadiplomcloud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sobinda.javadiplomcloud.entity.User;
import sobinda.javadiplomcloud.model.Login;
import sobinda.javadiplomcloud.service.UserService;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class RegistrationController {
    Logger logger;
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Validated User userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        if (!userService.saveUser(userForm)) {
            model.addAttribute("userLoginError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        return "redirect:/";
    }
}

