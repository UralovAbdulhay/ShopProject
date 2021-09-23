package uz.kapitalbank.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.kapitalbank.testing.entity.MyFile;

import java.util.Optional;

public interface MyFileRepository extends JpaRepository<MyFile, Long> {


    boolean deleteByHashId(String hashId);

    Optional<MyFile> findByHashId(String hashId);
}
