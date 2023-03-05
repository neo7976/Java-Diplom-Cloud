package sobinda.javadiplomcloud.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import sobinda.javadiplomcloud.dto.CloudFileDto;
import sobinda.javadiplomcloud.entity.CloudFileEntity;
import sobinda.javadiplomcloud.entity.UserEntity;
import sobinda.javadiplomcloud.model.Role;
import sobinda.javadiplomcloud.service.CloudService;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class CloudControllerTest {
    @Mock
    CloudService cloudService;

    @InjectMocks
    CloudController cloudController;
    UserEntity user;
    CloudFileEntity cloudFile;
    CloudFileDto cloudFileDto;
    private final int USER_ID = 100;
    private final int CLOUD_FILE_ID = 999;
    private final String FILE_NAME = "testFile.pdf";

    @BeforeEach
    void setUp() {
        System.out.println("Начало теста");
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
        System.out.println("Окончание теста");
    }

    public static Stream<Arguments> uploadFile() {
        return Stream.of(
                Arguments.of(true,
                        new ResponseEntity<>(OK)),
                Arguments.of(false,
                        new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR))
        );
    }

    @ParameterizedTest
    @MethodSource("uploadFile")
    void uploadFileTest(boolean flag, ResponseEntity<Void> expected) {
        MultipartFile mf = new MockMultipartFile(
                "testFile",
                FILE_NAME,
                MediaType.TEXT_PLAIN_VALUE,
                "testUploadFile".getBytes()
        );
        when(cloudService.uploadFile(mf, FILE_NAME)).thenReturn(flag);
        var result = cloudController.uploadFile(mf, FILE_NAME);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("uploadFile")
    void deleteFileTest(boolean flag, ResponseEntity<Void> expected) {
        when(cloudService.deleteFile(FILE_NAME)).thenReturn(flag);
        var result = cloudController.deleteFile(FILE_NAME);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void getFileTest() {
        var expected = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + cloudFileDto.getFileName() + "\"")
                .body(cloudFileDto.getResource());

        when(cloudService.getFile(FILE_NAME)).thenReturn(cloudFileDto);
        var result = cloudController.getFile(FILE_NAME);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("uploadFile")
    void putFileTest(boolean flag, ResponseEntity<Void> expected) {
        when(cloudService.putFile(FILE_NAME, cloudFileDto)).thenReturn(flag);
        var result = cloudController.putFile(FILE_NAME, cloudFileDto);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void getAllFileTest() {
        var list = List.of(cloudFileDto);
        when(cloudService.getAllFile()).thenReturn(list);
        var result = cloudController.getAllFile();
        Assertions.assertEquals(ResponseEntity.ok(list), result);
    }
}