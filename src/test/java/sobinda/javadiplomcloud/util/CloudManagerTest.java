package sobinda.javadiplomcloud.util;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sobinda.javadiplomcloud.entity.CloudFile;
import sobinda.javadiplomcloud.entity.User;
import sobinda.javadiplomcloud.entity.Role;

import java.io.File;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

class CloudManagerTest {
    CloudManager cloudManager;
    User user;
    CloudFile cloudFile;
    UUID uuid = UUID.fromString("aaa7e24d-bb2d-4885-900e-10771cdb7491");
    private final String text = "Хай\nПроверка 2 строк";

    @BeforeEach
    void setUp() {
        cloudManager = new CloudManager();
        user = User.builder()
                .login("111@yandex.ru")
                .password("1111")
                .roles(Set.of(new Role()))
                .build();
        System.out.println(user);

        cloudFile = CloudFile.builder()
                .fileName("testFile.txt")
                .date(Instant.now())
                .size(11L)
                .key(uuid)
                .user(user)
                .build();
        System.out.println(cloudFile);
    }

    @AfterEach
    void tearDown() {
        cloudManager = null;
        user = null;
        cloudFile = null;
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
        cloudManager.upload(text, cloudFile.getKey().toString(), cloudFile.getFileName());
        File file = new File("src/main/resources/static/users/" + cloudFile.getKey().toString() + "/" + cloudFile.getFileName());
        System.out.println("Проверка пути к файлу:  " + file.toString());
        boolean result = file.exists();
        Assertions.assertTrue(result);
    }

    @Test
    void getFileTest() {
        var result = cloudManager.getFile(cloudFile);
        Assertions.assertEquals(text, result);
    }
}