package sobinda.javadiplomcloud.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sobinda.javadiplomcloud.entity.CloudFile;
import sobinda.javadiplomcloud.service.CloudService;

import java.util.List;

@RestController
//@RequestMapping("/")
public class CloudController {

    private final CloudService cloudService;

    public CloudController(CloudService cloudService) {
        this.cloudService = cloudService;
    }

    //продумать
    @PostMapping("/file")
    public ResponseEntity<CloudFile> uploadFile(@RequestParam MultipartFile multipartFile) {
        return new ResponseEntity<>(cloudService.uploadFile(multipartFile), HttpStatus.CREATED);
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
    @PutMapping("/file")
    public String putFile() {
        return cloudService.putFile();
    }

    //продумать
    @GetMapping("/list")
    public String getAllFile() {
        return cloudService.getAllFile();
    }

}
