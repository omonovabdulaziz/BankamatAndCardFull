package uz.pdp.bankamat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.pdp.bankamat.entity.Kupyuralar;
import uz.pdp.bankamat.payload.ApiResponse;
import uz.pdp.bankamat.payload.KupyuralarDTO;
import uz.pdp.bankamat.service.KupyuralarService;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@RestController
@RequestMapping("/api/kupyuralar")
public class KupyuralarController {
    @Autowired
    KupyuralarService kupyuralarService;

    @Transactional
    @PostMapping
    public ResponseEntity<?> addKupyuralar(@RequestBody KupyuralarDTO kupyuralarDTO) {
        ApiResponse apiResponse = kupyuralarService.addKupyuralar(kupyuralarDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @Transactional
    @GetMapping("/verifyEmail")
    public ResponseEntity<?> verifyEmail(@RequestParam String emailCode, @RequestParam String email) {
        ApiResponse apiResponse = kupyuralarService.verifyEmail(email, emailCode);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/{id}")
    public Page<Kupyuralar> getKupyuralarbyBankId(@RequestParam int page , @RequestParam int count , @PathVariable Integer id){
        return kupyuralarService.getKupyuralar(page , count , id);
    }


}
