package net.mohamed.spring_app.service;

import jakarta.transaction.Transactional;
import net.mohamed.spring_app.dtos.NewPaymentDTO;
import net.mohamed.spring_app.entities.Payment;
import net.mohamed.spring_app.entities.PaymentStatus;
import net.mohamed.spring_app.entities.PaymentType;
import net.mohamed.spring_app.entities.Student;
import net.mohamed.spring_app.repository.PaymentRepository;
import net.mohamed.spring_app.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class PaymentService {

    private PaymentRepository paymentRepository;
    private StudentRepository studentRepository;

    public PaymentService(PaymentRepository paymentRepository, StudentRepository studentRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
    }
    public Payment savePayment(MultipartFile file
            , NewPaymentDTO newPaymentDTO) throws IOException {
        Path path = Paths.get(System.getProperty("user.home"),"students-app-files","payments");
        if(!Files.exists(path)){
            Files.createDirectories(path);
        }
        String fileId = UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"),"students-app-files","payments",fileId+".pdf");
        Files.copy(file.getInputStream(),filePath);
        Student student =studentRepository.findByCode(newPaymentDTO.getStudentCode());
        Payment payment = Payment.builder()
                .type(newPaymentDTO.getType())
                .student(student)
                .date(newPaymentDTO.getDate())
                .amount(newPaymentDTO.getAmount())
                .status(PaymentStatus.CREATED)
                .file(filePath.toUri().toString())
                .build();
        Payment savedPayment = paymentRepository.save(payment);
        return savedPayment;
    }
}
