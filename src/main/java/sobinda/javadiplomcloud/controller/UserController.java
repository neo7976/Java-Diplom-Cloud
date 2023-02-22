package sobinda.javadiplomcloud.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sobinda.javadiplomcloud.dto.UserDTO;
import sobinda.javadiplomcloud.model.Token;
import sobinda.javadiplomcloud.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("login")
    public ResponseEntity<Token> login(@RequestBody UserDTO userDTO) {
        log.info("Попытка авторизоваться на сервере");
        Token token = userService.login(userDTO);
        return ResponseEntity.ok(token);
    }
}
