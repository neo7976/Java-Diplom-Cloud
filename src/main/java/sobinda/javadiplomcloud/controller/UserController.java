package sobinda.javadiplomcloud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sobinda.javadiplomcloud.dto.UserDTO;
import sobinda.javadiplomcloud.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @PostMapping("login")
    public String login(@RequestBody UserDTO userDTO) {
        userService.login(userDTO);
        return "Привет";
    }
}
