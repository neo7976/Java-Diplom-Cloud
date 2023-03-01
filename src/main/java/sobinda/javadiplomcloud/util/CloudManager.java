package sobinda.javadiplomcloud.util;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import sobinda.javadiplomcloud.entity.CloudFileEntity;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

@NoArgsConstructor
//добавил для сервиса
@Component
public class CloudManager {
    //продумать, как задавать пути
    private final String DIRECTORY_PATH = "src/main/resources/static/users";

    public boolean upload(byte[] resource, String keyName, String fileName) throws IOException {
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
        return file.exists();
    }

    @SneakyThrows
    public byte[] getFile(CloudFileEntity cloudFileEntity) {
        return Files.readAllBytes(Paths.get(
                DIRECTORY_PATH,
                cloudFileEntity.getKey().toString(),
                cloudFileEntity.getFileName()
        ));
    }

    @SneakyThrows
    public boolean delete(CloudFileEntity cloudFile) {
        Path paths = Paths.get(DIRECTORY_PATH, cloudFile.getKey().toString());
        Files.walk(paths)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
        File file = new File(paths.toUri());
        return !file.exists();
    }

    public boolean renameFileTo(CloudFileEntity oldCloudFileEntity, String renameFileName) {
        Path paths = Paths.get(DIRECTORY_PATH, oldCloudFileEntity.getKey().toString(), oldCloudFileEntity.getFileName());
        File file = new File(paths.toUri());
        Path renamePaths = Paths.get(DIRECTORY_PATH, oldCloudFileEntity.getKey().toString(), renameFileName);
        File renameFile = new File(renamePaths.toUri());
        System.out.println(renamePaths);
        return file.renameTo(renameFile);
    }
}
