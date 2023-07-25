package uz.pdp.bankamat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.bankamat.entity.Bankamat;
import uz.pdp.bankamat.payload.ApiResponse;
import uz.pdp.bankamat.payload.BankamatDTO;
import uz.pdp.bankamat.service.BankamatService;

@RestController
@RequestMapping("/api/bankamat")
public class BankamatController {
    @Autowired
    BankamatService bankamatService;

    //create
    @PostMapping
    public ResponseEntity<?> addBankamat(@RequestBody BankamatDTO bankamatDTO) {
        ApiResponse apiResponse = bankamatService.addBankamat(bankamatDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    //read
    @GetMapping
    public Page<Bankamat> getBankamatPage(@RequestParam int page, @RequestParam int count) {
        return bankamatService.getBankamat(page, count);
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBankamat(@PathVariable Integer id, @RequestBody BankamatDTO bankamatDTO) {
        ApiResponse apiResponse = bankamatService.updateBankamat(id, bankamatDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBankamat(@PathVariable  Integer id) {
        ApiResponse apiResponse = bankamatService.deleteBankamat(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
}
