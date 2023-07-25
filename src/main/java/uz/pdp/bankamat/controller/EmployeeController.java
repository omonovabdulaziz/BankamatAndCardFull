package uz.pdp.bankamat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.pdp.bankamat.payload.ApiResponse;
import uz.pdp.bankamat.payload.EmployeeRegisterDTO;
import uz.pdp.bankamat.service.EmployeeService;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;


    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(@RequestBody EmployeeRegisterDTO employeeRegisterDTO) {
        ApiResponse apiResponse = employeeService.registerEmployee(employeeRegisterDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<?> updateEmployee(@PathVariable String email, @RequestBody EmployeeRegisterDTO employeeRegisterDTO) {
        ApiResponse apiResponse = employeeService.updateEmployee(email, employeeRegisterDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @Transactional
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String email) {
        ApiResponse apiResponse = employeeService.deleteEmployee(email);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }
}
