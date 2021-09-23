package uz.kapitalbank.testing.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.kapitalbank.testing.entity.MyFile;
import uz.kapitalbank.testing.exceptions.BadRequestException;
import uz.kapitalbank.testing.exceptions.ResourceNotFoundException;
import uz.kapitalbank.testing.model.Result;
import uz.kapitalbank.testing.repository.MyFileRepository;
import uz.kapitalbank.testing.service.MyFileService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MyFileServiceImpl implements MyFileService {


    private final MyFileRepository repository;

    @Value("${upload}")
    private String downloadPath;


    public ResponseEntity save(MultipartFile multipartFile) {

        MyFile myFile = new MyFile();

        myFile.setContentType(multipartFile.getContentType());
        myFile.setFileSize(multipartFile.getSize());
        myFile.setName(multipartFile.getOriginalFilename());
        myFile.setExtension(getExtension(myFile.getName()).toLowerCase());
        myFile.setHashId(UUID.randomUUID().toString());


        LocalDate date = LocalDate.now();

        // change value downloadPath
        String localPath = downloadPath + String.format(
                "/%d/%d/%d/%s",
                date.getYear(),
                date.getMonthValue(),
                date.getDayOfMonth(),
                myFile.getExtension().toLowerCase());

        myFile.setUploadPath(localPath);


        // downloadPath / year / month / day / extension
        File file = new File(localPath);

        // " downloadPath / year / month / day / extension "   crate directory
        file.mkdirs();

        // save MyFile into base
        repository.save(myFile);

        try {

            // copy bytes into new file or saving into storage
            multipartFile.transferTo(new File(file.getAbsolutePath() + "/" + String.format("%s.%s", myFile.getHashId(), myFile.getExtension())));

            Map<Object, Object> data = new HashMap<>();
            data.put("hashId", myFile.getHashId());
            return ResponseEntity.ok(new Result(true, "File successfully saved!", data));

        } catch (IOException e) {
            e.printStackTrace();
            throw new BadRequestException("File not saved!");
        }
    }


    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }


    @Override
    public MyFile findByHashId(String hashId) {
        return repository.findByHashId(hashId)
                .orElseThrow(() -> new ResourceNotFoundException("File not found by hashId = " + hashId));
    }


    @Override
    public ResponseEntity delete(String hashId) {
        MyFile myFile = findByHashId(hashId);

        File file = new File(String.format("%s/%s.%s", myFile.getUploadPath(), myFile.getHashId(), myFile.getExtension()));

        if (file.delete() && repository.deleteByHashId(hashId)) {

            return ResponseEntity.ok(new Result(true, "File successfully deleted"));

        } else {
            return ResponseEntity.badRequest().body(
                    new Result(
                            false,
                            "File not deleted by hashId = " + hashId
                    )
            );
        }

    }


}
