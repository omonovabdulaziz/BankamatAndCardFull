package uz.pdp.bankamat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.bankamat.entity.Role;
import uz.pdp.bankamat.entity.enums.RoleName;

public interface RoleRepository extends JpaRepository<Role ,Integer> {
Role findByRoleName(RoleName roleName);
}
