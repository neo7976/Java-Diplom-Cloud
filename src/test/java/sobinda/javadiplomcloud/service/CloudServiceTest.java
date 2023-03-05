package sobinda.javadiplomcloud.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import sobinda.javadiplomcloud.dto.CloudFileDto;
import sobinda.javadiplomcloud.entity.CloudFileEntity;
import sobinda.javadiplomcloud.entity.UserEntity;
import sobinda.javadiplomcloud.model.Role;
import sobinda.javadiplomcloud.repository.CloudRepository;
import sobinda.javadiplomcloud.security.JWTToken;
import sobinda.javadiplomcloud.util.CloudManager;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@DataJpaTest
@ExtendWith(MockitoExtension.class)
class CloudServiceTest {
    @Mock
    CloudManager cloudManager;
    @Mock
    JWTToken jwtToken;
    @Mock
    CloudRepository cloudRepository;
    @InjectMocks
    CloudService cloudService;

    //    @Mock
//    TestEntityManager entityManager;
    UserEntity user;
    CloudFileEntity cloudFile;
    CloudFileDto cloudFileDto;
    private final int USER_ID = 100;
    private final int CLOUD_FILE_ID = 999;
    private final String FILE_NAME = "testFile.pdf";

    @BeforeEach
    void setUp() {
        user = UserEntity.builder()
                .id(USER_ID)
                .login("test@yandex.ru")
                .password("$2a$10$L4cA.wDXaxBESV/FUGchT.WyEFX6qgMrdGGjDl7kt9QMFVWobi5Ne")
                .roles(Set.of(Role.ROLE_WRITE))
                .build();

        cloudFile = CloudFileEntity.builder()
                .id(CLOUD_FILE_ID)
                .date(Instant.now())
                .size(265L)
                .key(UUID.randomUUID())
                .fileName(FILE_NAME)
                .build();

        cloudFileDto = CloudFileDto.builder()
                .fileName(FILE_NAME)
                .resource("TESTING".getBytes())
                .build();
    }

    @AfterEach
    void tearDown() {
        user = null;
        cloudFile = null;
    }

    @SneakyThrows
    @Test
    void uploadFileTest() {
        MultipartFile mf = new MockMultipartFile(
                "testFile",
                FILE_NAME,
                MediaType.TEXT_PLAIN_VALUE,
                "testUploadFile".getBytes()
        );

        when(jwtToken.getAuthenticatedUser()).thenReturn(user);
        when(cloudManager.upload(any(), any(), any())).thenReturn(true);
        when(cloudRepository.save(any(CloudFileEntity.class))).thenReturn(cloudFile);

        boolean result = cloudService.uploadFile(mf, FILE_NAME);
        Assertions.assertTrue(result);
    }

    @Test
    void deleteFileTest() {
        when(cloudRepository.findCloudFileEntityByFileName(FILE_NAME)).thenReturn(Optional.of(cloudFile));
        when(cloudManager.delete(any(CloudFileEntity.class))).thenReturn(true);

        var result = cloudService.deleteFile(FILE_NAME);
        Assertions.assertTrue(result);
    }

    @Test
    void getFileTest() {
        when(cloudRepository.findCloudFileEntityByFileName(FILE_NAME)).thenReturn(Optional.of(cloudFile));
        when(cloudManager.getFile(any(CloudFileEntity.class))).thenReturn(cloudFileDto.getResource());

        var result = cloudService.getFile(FILE_NAME);
        Assertions.assertEquals(cloudFileDto, result);
    }

    @Test
    void putFileTest() {
        String renameFile = "RenameFile.pdf";
        when(cloudRepository.findCloudFileEntityByFileName(FILE_NAME)).thenReturn(Optional.of(cloudFile));
        CloudFileDto cloudFileDtoRename = CloudFileDto.builder()
                .fileName(renameFile)
                .build();
        when(cloudManager.renameFileTo(cloudFile, renameFile)).thenReturn(true);

        var result = cloudService.putFile(FILE_NAME, cloudFileDtoRename);
        Assertions.assertTrue(result);
    }

    @Test
    void getAllFile() {
        var result = cloudService.getAllFile();
        for (CloudFileDto fileDto : result) {
            Assertions.assertEquals(cloudFileDto, fileDto);
        }
    }
}