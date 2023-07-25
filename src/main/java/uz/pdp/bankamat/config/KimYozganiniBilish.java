package uz.pdp.bankamat.config;


import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import uz.pdp.bankamat.entity.Employee;

import java.util.Optional;
import java.util.UUID;

public class KimYozganiniBilish implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof Employee) {
            Employee employee = (Employee) principal;
            return Optional.of(employee.getUsername());
        } else if (principal instanceof User) {
            User user = (User) principal;
            return Optional.of(user.getUsername());
        }

        return Optional.empty();
    }

}
