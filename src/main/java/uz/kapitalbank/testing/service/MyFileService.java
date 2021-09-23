package uz.kapitalbank.testing.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import uz.kapitalbank.testing.entity.MyFile;
import uz.kapitalbank.testing.model.Result;

public interface MyFileService {

    ResponseEntity save(MultipartFile multipartFile);

    MyFile findByHashId(String hashId);

    ResponseEntity delete(String hashId);

}
