package sobinda.javadiplomcloud.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sobinda.javadiplomcloud.entity.CloudFileEntity;
import sobinda.javadiplomcloud.service.CloudService;

import javax.validation.constraints.NotNull;

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
        cloudService.uploadFile(multipartFile, fileName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //продумать
    @DeleteMapping("/file")
    public String deleteFile() {
        return cloudService.deleteFile();
    }

    //продумать, какой список
    @GetMapping("/file/{id}")
    public String getFile(@PathVariable Integer id) {
        return cloudService.getFile(id);
    }

    //продумать
    @PutMapping("/file/{id}")
    public String putFile(@PathVariable Integer id) {
        return cloudService.putFile();
    }

    //продумать
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<CloudFileEntity> getAllFile() {
        cloudService.getAllFile();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
