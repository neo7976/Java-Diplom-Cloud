package sobinda.javadiplomcloud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sobinda.javadiplomcloud.model.Login;
import sobinda.javadiplomcloud.service.UsersService;

import java.util.logging.Level;
import java.util.logging.Logger;
//@CrossOrigin("")
@RestController
//@RequestMapping("/")
@RequiredArgsConstructor
public class UsersController {
    Logger logger;
    UsersService usersService;

//    @PostMapping("/login")
    @PostMapping("/login")
    public String login(@Validated @RequestBody Login login) {
        logger.log(Level.INFO, "Пользователь пробует авторизоваться");
        usersService.login(login);
        return null;
    }
}
