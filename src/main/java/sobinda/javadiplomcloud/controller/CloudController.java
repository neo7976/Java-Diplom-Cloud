package sobinda.javadiplomcloud.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public String uploadFile() {
        return null;
    }

    //продумать
    @DeleteMapping("/file")
    public String deleteFile() {
        return null;
    }
    //продумать, какой список
    @GetMapping("/file")
    public List<String> getFile() {
        return null;
    }
    //продумать
    @PutMapping("/file")
    public String putFile() {
        return null;
    }
    //продумать
    @GetMapping("/list")
    public String getAllFile(){
        return null;
    }

}
