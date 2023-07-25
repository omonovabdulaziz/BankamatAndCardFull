package uz.pdp.bankamat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.pdp.bankamat.entity.ProccesPayment;
import uz.pdp.bankamat.payload.ApiResponse;
import uz.pdp.bankamat.payload.ProccessPaymentDTO;
import uz.pdp.bankamat.service.ProccessPaymentService;

@RestController
@RequestMapping("/api/proccesspayment")
@Transactional
public class ProccessPaymentController {
    @Autowired
    ProccessPaymentService proccessPaymentService;


//    @PreAuthorize("hasAnyRole('HODIM' , 'DIREKTOR' , 'USER')")
    @PostMapping
    public ResponseEntity<?> addProccessPayment(@RequestBody ProccessPaymentDTO proccessPaymentDTO) {
        ApiResponse apiResponse = proccessPaymentService.addProccessPayment(proccessPaymentDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }


    //    @PreAuthorize("hasAnyRol('DIREKTOR' , 'HODIM')")
    @GetMapping("/{bankamatId}")
    public Page<ProccesPayment> getPageProccessPayment(@RequestParam int page, @RequestParam int count, @PathVariable Integer bankamatId) {
        return proccessPaymentService.getPageProccessPayment(page, count, bankamatId);
    }



    //UPDATEABLE AND DELETEABLE FALSE;
}
