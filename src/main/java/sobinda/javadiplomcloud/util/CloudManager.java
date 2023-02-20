package sobinda.javadiplomcloud.util;

import ch.qos.logback.core.util.FileUtil;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Component;
import sobinda.javadiplomcloud.entity.CloudFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
//добавил для сервиса
@Component
public class CloudManager {
    //продумать, как задавать пути
    private final String DIRECTORY_PATH = "src/main/resources/static/users";

    //
//    public void upload(byte[] resource, String keyName, String fileName) throws IOException {
    public void upload(String resource, String keyName, String fileName) throws IOException {
        File file = new File(DIRECTORY_PATH + "/" + keyName + "/" + fileName);
        if (!file.exists()) {
            boolean folderPath = new File(DIRECTORY_PATH).mkdir();
            boolean folder = new File(DIRECTORY_PATH + "/" + keyName).mkdir();
            file.createNewFile();
        }
//        FileOutputStream stream = null;
//
//        try {
//            stream = new FileOutputStream(file.toString());
//            stream.write(resource);
//        } finally {
//            stream.close();
//        }
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(file));
            bf.write(resource);
            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFile(CloudFile cloudFile) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader it = new BufferedReader(new FileReader(DIRECTORY_PATH +
                    "/" + cloudFile.getKey().toString() +
                    "/" + cloudFile.getFileName()));
            String line;
            while ((line = it.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            it.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //убираем последний \n
        sb.setLength(sb.length() - 1);

        String result = sb.toString();
        System.out.println(result);
        return result;
    }
}
