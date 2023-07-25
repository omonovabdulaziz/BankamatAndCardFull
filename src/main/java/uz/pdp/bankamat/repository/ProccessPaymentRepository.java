package uz.pdp.bankamat.repository;

import org.apache.tomcat.jni.Proc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.bankamat.entity.ProccesPayment;

import java.util.UUID;

public interface ProccessPaymentRepository extends JpaRepository<ProccesPayment , UUID> {
    Page<ProccesPayment> findByBankamatId(Integer bankamat_id, Pageable pageable);
}
