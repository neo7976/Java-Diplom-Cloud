package sobinda.javadiplomcloud.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sobinda.javadiplomcloud.dto.UserDTO;
import sobinda.javadiplomcloud.repository.UsersRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UsersRepository usersRepository;

    public void login(UserDTO userDTO) {
        var userFound = usersRepository.findByLogin(userDTO.getLogin());
        if (userFound.isPresent()) {
            log.info("Пользователь {} найден\n Его данные: {}", userDTO.getLogin(), userFound);
        }
    }
}
