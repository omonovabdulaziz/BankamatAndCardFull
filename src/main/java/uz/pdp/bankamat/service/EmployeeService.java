package uz.pdp.bankamat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.bankamat.entity.Employee;
import uz.pdp.bankamat.entity.enums.RoleName;
import uz.pdp.bankamat.payload.ApiResponse;
import uz.pdp.bankamat.payload.EmployeeRegisterDTO;
import uz.pdp.bankamat.repository.EmployeeRepository;
import uz.pdp.bankamat.repository.RoleRepository;

import java.util.*;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;

    //create register
    public ApiResponse registerEmployee(EmployeeRegisterDTO employeeRegisterDTO) {
        if (employeeRepository.existsByEmail(employeeRegisterDTO.getEmail()))
            return new ApiResponse("Bunday emailli employee bor ", false);


        Employee employee = new Employee();
        employee.setEmail(employeeRegisterDTO.getEmail());
        employee.setRole(Collections.singleton(roleRepository.findByRoleName(RoleName.ROLE_USER)));
        employee.setFullName(employeeRegisterDTO.getFullName());
        employee.setPassword(passwordEncoder.encode(employeeRegisterDTO.getPassword()));
        employeeRepository.save(employee);
        return new ApiResponse("saqlandi", true);
    }


    //update
    public ApiResponse updateEmployee(String email, EmployeeRegisterDTO employeeRegisterDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(email);
        if (!optionalEmployee.isPresent())
            return new ApiResponse("tahrirlanmoqchi bolgan employee topilmadi", false);
        if (employeeRepository.existsByEmail(employeeRegisterDTO.getEmail()))
            return new ApiResponse("bunday emailli employee bor ", false);


        Employee editedEmployee = optionalEmployee.get();

        editedEmployee.setEmail(employeeRegisterDTO.getEmail());
        editedEmployee.setPassword(employeeRegisterDTO.getPassword());
        editedEmployee.setFullName(employeeRegisterDTO.getFullName());

        employeeRepository.save(editedEmployee);
        return new ApiResponse("tahrirlandi", true);
    }

    public ApiResponse deleteEmployee(String email) {
        try {
            if (employeeRepository.findByEmail(email).isEmpty())
                return new ApiResponse("ochirish uchun email topilmadi" , false);
            employeeRepository.deleteByEmail(email);
            return new ApiResponse("delete"  , true);
        }catch (Exception e){
            return new ApiResponse("Amaliyot xatolik bilan yakunlandi" , false);
        }
    }
}
