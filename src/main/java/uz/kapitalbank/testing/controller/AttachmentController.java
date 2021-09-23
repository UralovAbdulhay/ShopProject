package uz.kapitalbank.testing.controller;



import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.kapitalbank.testing.entity.MyFile;
import uz.kapitalbank.testing.model.Result;
import uz.kapitalbank.testing.service.impl.MyFileServiceImpl;

import java.net.MalformedURLException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AttachmentController {
    @Value("${upload}")
    private String uploadFolder;

    private final MyFileServiceImpl myFileService;

    @PostMapping("/save-attachment")
    public ResponseEntity saveFile(@RequestParam(name = "file") MultipartFile multipartFile) {
        return myFileService.save(multipartFile);
    }


    @GetMapping("/preview/{hashId}")
    public ResponseEntity preview(@PathVariable String hashId) throws MalformedURLException {
        MyFile myFile = myFileService.findByHashId(hashId);
        return ResponseEntity.ok()
                .header(HttpHeaders.EXPIRES, "inline; fileName=" + URLEncoder.encode(myFile.getName()))
                .contentType(MediaType.parseMediaType(myFile.getContentType()))
                .body(new FileUrlResource(String.format("%s/%s.%s", myFile.getUploadPath(), myFile.getHashId(), myFile.getExtension())));
    }

    @GetMapping("/download/{hashId}")
    public ResponseEntity download(@PathVariable String hashId) throws MalformedURLException {
        MyFile myFile = myFileService.findByHashId(hashId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=" + URLEncoder.encode(myFile.getName()))
                .contentType(MediaType.parseMediaType(myFile.getContentType()))
                .body(new FileUrlResource(String.format("%s/%s.%s", myFile.getUploadPath(), myFile.getHashId(), myFile.getExtension())));
    }

    @DeleteMapping("/delete/{hashId}")
    public ResponseEntity delete(@PathVariable String hashId) throws MalformedURLException {
        return myFileService.delete(hashId);
    }
}
