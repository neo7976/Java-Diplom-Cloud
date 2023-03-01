package sobinda.javadiplomcloud.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;
import sobinda.javadiplomcloud.entity.UserEntity;
import sobinda.javadiplomcloud.model.Role;
import sobinda.javadiplomcloud.repository.CloudRepository;
import sobinda.javadiplomcloud.repository.UsersRepository;
import sobinda.javadiplomcloud.security.JWTToken;
import sobinda.javadiplomcloud.util.CloudManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@DataJpaTest
@ExtendWith(MockitoExtension.class)
class CloudServiceTest {
    @Mock
    CloudManager cloudManager;
    @Mock
    JWTToken jwtToken;
    @InjectMocks
    CloudService cloudService;
    @Autowired
    CloudRepository cloudRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    TestEntityManager entityManager;
    UserEntity user;
    private final int USER_ID = 100;
    private final String FILE_NAME = "testFile.pdf";

    @BeforeEach
    void setUp() {
        user = UserEntity.builder()
                .id(USER_ID)
                .login("test@yandex.ru")
                .password("$2a$10$L4cA.wDXaxBESV/FUGchT.WyEFX6qgMrdGGjDl7kt9QMFVWobi5Ne")
                .roles(Set.of(Role.ROLE_WRITE))
                .build();
        System.out.println(user);
//        this.entityManager.persistAndFlush(user);
    }

    @AfterEach
    void tearDown() {
        cloudService = null;
        cloudRepository = null;
        cloudManager = null;
    }

    @SneakyThrows
    @Test
    void uploadFile() {
        MultipartFile mf = new MockMultipartFile(
                "testFile",
                FILE_NAME,
                MediaType.TEXT_PLAIN_VALUE,
                "testUploadFile".getBytes()
        );
//
        when(jwtToken.getAuthenticatedUser()).thenReturn(user);
        when(cloudManager.upload(any(), any(), any())).thenReturn(true);

        boolean result = cloudService.uploadFile(mf, FILE_NAME);
        Assertions.assertTrue(result);
    }

    @Test
    void deleteFile() {
    }

    @Test
    void getFile() {
    }

    @Test
    void putFile() {
    }

    @Test
    void getAllFile() {
    }
}