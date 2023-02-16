package sobinda.javadiplomcloud.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sobinda.javadiplomcloud.service.CloudService;

import java.util.List;

@RestController
@RequestMapping("/")
public class CloudController {

    private final CloudService cloudService;

    public CloudController(CloudService cloudService) {
        this.cloudService = cloudService;
    }

    //продумать
    @PostMapping("/file")
    public String uploadFile(@RequestParam MultipartFile multipartFile) {
        return cloudService.uploadFile(multipartFile);
    }

    //продумать
    @DeleteMapping("/file")
    public String deleteFile() {
        return cloudService.deleteFile();
    }

    //продумать, какой список
    @GetMapping("/file")
    public List<String> getFile() {
        return cloudService.getFile();
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
