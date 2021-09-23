package uz.kapitalbank.testing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.kapitalbank.testing.model.Result;
import uz.kapitalbank.testing.service.impl.InvoiceServiceImpl;

@RestController
@RequestMapping("/api/invoice")

@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceServiceImpl invoiceService;

    public ResponseEntity expiredInvoices(){
        return ResponseEntity.ok(new Result(true, invoiceService.findAllExpiredInvoices()));
    }


}
