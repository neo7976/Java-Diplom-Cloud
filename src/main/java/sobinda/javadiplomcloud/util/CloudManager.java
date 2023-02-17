package sobinda.javadiplomcloud.util;

import ch.qos.logback.core.util.FileUtil;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@NoArgsConstructor
//добавил для сервиса
@Component
public class CloudManager {
    //продумать, как задавать пути
    private final String DIRECTORY_PATH = "src/main/resources/static/users";

    public void upload(byte[] resource, String keyName, String fileName) throws IOException {
        File file = new File(DIRECTORY_PATH + "/" + keyName + "/" + fileName);
        if (!file.exists()) {
            boolean folderPath = new File(DIRECTORY_PATH).mkdir();
            boolean folder = new File(DIRECTORY_PATH + "/" + keyName).mkdir();
            file.createNewFile();
        }
        FileOutputStream stream = null;

        try {
            stream = new FileOutputStream(file.toString());
            stream.write(resource);
        } finally {
            stream.close();
        }
    }
}
