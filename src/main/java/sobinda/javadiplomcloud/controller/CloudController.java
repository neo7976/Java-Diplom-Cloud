package sobinda.javadiplomcloud.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sobinda.javadiplomcloud.dto.CloudFileDto;
import sobinda.javadiplomcloud.service.CloudService;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
//@CrossOrigin("http//localhost:8000")
//@RequestMapping("/")
public class CloudController {

    private final CloudService cloudService;

    //    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/file")
    public ResponseEntity<Void> uploadFile(@NotNull @RequestParam("file") MultipartFile multipartFile,
                                           @RequestParam("filename") String fileName) {
        log.info("Получили файл на загрузку: {}", fileName);
        if (cloudService.uploadFile(multipartFile, fileName)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/file{filename}")
    public ResponseEntity<Void> deleteFile(@RequestParam String filename) {
        log.info("Начинаем искать файл {} для удаления", filename);
        if (cloudService.deleteFile(filename)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/file{filename}")
    public ResponseEntity<byte[]> getFile(@RequestParam String filename) {
        log.info("Запрос на получение скачивания файла {}", filename);
        var cloudFileDto = cloudService.getFile(filename);
        log.info("Запрос на скачивание файла {} получен. Начинаем скачивание...", filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + cloudFileDto.getFileName() + "\"")
                .body(cloudFileDto.getResource());
    }

    //продумать
    @PutMapping("/file{filename}")
    public ResponseEntity<Void> putFile(@RequestParam String filename, @RequestBody CloudFileDto cloudFileDto) {
        cloudService.putFile(filename, cloudFileDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<CloudFileDto>> getAllFile() {
        var result = cloudService.getAllFile();
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }
}
