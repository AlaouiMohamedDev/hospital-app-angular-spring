package net.mohamed.spring_app.web;

import net.mohamed.spring_app.dtos.NewPaymentDTO;
import net.mohamed.spring_app.entities.Payment;
import net.mohamed.spring_app.entities.PaymentStatus;
import net.mohamed.spring_app.entities.PaymentType;
import net.mohamed.spring_app.entities.Student;
import net.mohamed.spring_app.repository.PaymentRepository;
import net.mohamed.spring_app.repository.StudentRepository;
import net.mohamed.spring_app.service.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class StudentRestController {

    private PaymentRepository paymentRepository;
    private StudentRepository studentRepository;
    private PaymentService paymentService;

    public StudentRestController(PaymentService paymentService,PaymentRepository paymentRepository, StudentRepository studentRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
        this.paymentService = paymentService;
    }

    @GetMapping("/payments")
    public List<Payment> allPayment(){
        return paymentRepository.findAll();
    }

    @GetMapping("/payments/{id}")
    private Payment findById(@PathVariable Long id){
        return paymentRepository.findById(id).get();
    }

    @GetMapping("/students")
    public List<Student> allStudents(){
        return studentRepository.findAll();
    }
    @GetMapping("/students/{id}")
    public Student findById(@PathVariable String id){
        return studentRepository.findById(id).get();
    }

    @GetMapping("/students/{code}/payments")
    public List<Payment> findByStudentCode(@PathVariable String code){
        return paymentRepository.findByStudentCode(code);
    }

    @PostMapping(value = "/payments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam("file") MultipartFile file
            , NewPaymentDTO newPaymentDTO) throws IOException {
        return paymentService.savePayment(file,newPaymentDTO);
    }

    @GetMapping(value = "paymentFile/{paymentId}" , produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable Long paymentId) throws IOException {
        Payment payment = paymentRepository.findById(paymentId).get();
        String filePath = payment.getFile();
        return Files.readAllBytes(Path.of(URI.create(filePath)));
    }

    @PutMapping("/payments/updateStatus/{paymentId}")
    public Payment updatePaymentStatus(@RequestParam PaymentStatus status,@PathVariable Long paymentId){
        Payment payment = paymentRepository.findById(paymentId).get();
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }
}
