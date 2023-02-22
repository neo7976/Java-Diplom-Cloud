package sobinda.javadiplomcloud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sobinda.javadiplomcloud.repository.UsersRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
}
