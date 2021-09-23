package uz.kapitalbank.testing.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.kapitalbank.testing.model.Result;
import uz.kapitalbank.testing.service.CategoryService;

@RestController
@RequestMapping("/api/category")

@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/list")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(new Result(true, categoryService.findAllByActive()));
    }


    @GetMapping
    public ResponseEntity findByProductId(@RequestParam(name = "product_id") Long id) {
        return ResponseEntity.ok(new Result(true, categoryService.findByProductId(id)));
    }


}
