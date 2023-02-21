package sobinda.javadiplomcloud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sobinda.javadiplomcloud.model.Login;
import sobinda.javadiplomcloud.repository.UsersRepository;

@Service
@RequiredArgsConstructor
public class UsersService {

    UsersRepository usersRepository;

    public String login(Login login) {
        var result = usersRepository.findUserByLogin(login.getLogin());
        return result.get().toString();
    }
}
