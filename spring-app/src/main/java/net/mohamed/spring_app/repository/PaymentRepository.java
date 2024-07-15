package net.mohamed.spring_app.repository;

import net.mohamed.spring_app.entities.Payment;
import net.mohamed.spring_app.entities.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus status);
}
