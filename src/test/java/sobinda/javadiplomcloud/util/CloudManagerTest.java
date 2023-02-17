package sobinda.javadiplomcloud.util;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

class CloudManagerTest {
    CloudManager cloudManager;

    @BeforeEach
    void setUp() {
        cloudManager = new CloudManager();
    }

    @AfterEach
    void tearDown() {
        cloudManager = null;
    }

    @SneakyThrows
    @Test
    void upload() {
        byte[] test = new byte[]{0, 5, 4, 5, 4, 4, 12, 2, 5, 4, 1};
        String key = "testKey";
        String fileName = "testFile.pdf";
        cloudManager.upload(test, key, fileName);
        File file = new File("src/main/resources/static/users/" + key + "/" + fileName);
        boolean result = file.exists();
        Assertions.assertTrue(result);
        //добавить удаление файла после проверки, возможно, в другом методе
    }
}