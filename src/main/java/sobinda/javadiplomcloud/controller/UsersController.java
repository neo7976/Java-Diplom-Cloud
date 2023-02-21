package sobinda.javadiplomcloud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sobinda.javadiplomcloud.model.Login;
import sobinda.javadiplomcloud.service.UserService;

import java.util.logging.Level;
import java.util.logging.Logger;
//@CrossOrigin("")
@RestController
//@RequestMapping("/")
@RequiredArgsConstructor
public class UsersController {
    Logger logger;
    UserService userService;

//    @PostMapping("/login")
    @PostMapping("/login")
    public String login(@Validated @RequestBody Login login) {
        logger.log(Level.INFO, "Пользователь пробует авторизоваться");
        userService.login(login);
        return null;
    }
}
