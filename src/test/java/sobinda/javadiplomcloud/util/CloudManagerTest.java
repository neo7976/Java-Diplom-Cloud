package sobinda.javadiplomcloud.util;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sobinda.javadiplomcloud.entity.CloudFileEntity;
import sobinda.javadiplomcloud.entity.UserEntity;
import sobinda.javadiplomcloud.model.Role;

import java.io.File;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

class CloudManagerTest {
    CloudManager cloudManager;
    UserEntity userEntity;
    CloudFileEntity cloudFileEntity;
    UUID uuid = UUID.fromString("aaa7e24d-bb2d-4885-900e-10771cdb7491");
    private final String text = "Хай\nПроверка 2 строк";

    @BeforeEach
    void setUp() {
        cloudManager = new CloudManager();
        userEntity = UserEntity.builder()
                .login("111@yandex.ru")
                .password("1111")
                .roles(Set.of(Role.READ, Role.GUEST))
                .build();
        System.out.println(userEntity);

        cloudFileEntity = CloudFileEntity.builder()
                .fileName("testFile.txt")
                .date(Instant.now())
                .size(11L)
                .key(uuid)
                .userEntity(userEntity)
                .build();
        System.out.println(cloudFileEntity);
    }

    @AfterEach
    void tearDown() {
        cloudManager = null;
        userEntity = null;
        cloudFileEntity = null;
    }

//    @SneakyThrows
//    @Test
//    void uploadTest() {
//        byte[] test = new byte[]{0, 5, 4, 5, 4, 4, 12, 2, 5, 4, 1};
//        cloudManager.upload(test, cloudFile.getKey().toString(), cloudFile.getFileName());
//        File file = new File("src/main/resources/static/users/" + cloudFile.getKey().toString() + "/" + cloudFile.getFileName());
//        System.out.println("Проверка пути к файлу:  "  + file.toString());
//        boolean result = file.exists();
//        Assertions.assertTrue(result);
//        //добавить удаление файла после проверки, возможно, в другом методе
//    }

    @SneakyThrows
    @Test
    void uploadTest() {
        cloudManager.upload(text, cloudFileEntity.getKey().toString(), cloudFileEntity.getFileName());
        File file = new File("src/main/resources/static/users/" + cloudFileEntity.getKey().toString() + "/" + cloudFileEntity.getFileName());
        System.out.println("Проверка пути к файлу:  " + file.toString());
        boolean result = file.exists();
        Assertions.assertTrue(result);
    }

    @Test
    void getFileTest() {
        var result = cloudManager.getFile(cloudFileEntity);
        Assertions.assertEquals(text, result);
    }
}