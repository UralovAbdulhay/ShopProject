package uz.kapitalbank.testing.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.kapitalbank.testing.model.Result;
import uz.kapitalbank.testing.service.ProductService;

@RestController
@RequestMapping("/api/product")

@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/list")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(new Result(true, productService.findAll()));
    }


    @GetMapping("/details")
    public ResponseEntity findById(@RequestParam(name = "product_id") Long id) {
        return ResponseEntity.ok(new Result(true, productService.findById(id)));
    }



}
