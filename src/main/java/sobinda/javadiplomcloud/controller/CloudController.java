package sobinda.javadiplomcloud.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sobinda.javadiplomcloud.entity.CloudFileEntity;
import sobinda.javadiplomcloud.service.CloudService;

@RestController
//@RequestMapping("/")
public class CloudController {

    private final CloudService cloudService;

    public CloudController(CloudService cloudService) {
        this.cloudService = cloudService;
    }

    //продумать
    @PostMapping("/file")
    public ResponseEntity<CloudFileEntity> uploadFile(@RequestParam MultipartFile multipartFile) {
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
    @PutMapping("/file/{id}")
    public String putFile(@PathVariable Integer id) {
        return cloudService.putFile();
    }

    //продумать
    @GetMapping("/list")
    public String getAllFile() {
        return cloudService.getAllFile();
    }

}
