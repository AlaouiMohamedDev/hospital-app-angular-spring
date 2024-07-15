package net.mohamed.spring_app;

import net.mohamed.spring_app.entities.Payment;
import net.mohamed.spring_app.entities.PaymentStatus;
import net.mohamed.spring_app.entities.PaymentType;
import net.mohamed.spring_app.entities.Student;
import net.mohamed.spring_app.repository.PaymentRepository;
import net.mohamed.spring_app.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class SpringAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentRepository studentRepository, PaymentRepository paymentRepository){
		return args -> {
			studentRepository.save(Student
					.builder()
					.id(UUID.randomUUID().toString())
					.code("112233")
					.firstName("Mohamed")
					.build());
			studentRepository.save(Student
					.builder()
					.id(UUID.randomUUID().toString())
					.code("112244")
					.firstName("Karim")
					.build());
			studentRepository.save(Student
					.builder()
					.id(UUID.randomUUID().toString())
					.code("112255")
					.firstName("Mina")
					.build());

			PaymentType[] paymentTypes =PaymentType.values();
			Random random = new Random();
			studentRepository.findAll().forEach(st->{
				int index = random.nextInt(paymentTypes.length);
				for(int i = 0; i <10 ;i++){
					Payment payment = Payment.builder()
							.amount(1000+(int)(Math.random()*10000))
							.date(LocalDate.now())
							.type(paymentTypes[index])
							.file(UUID.randomUUID().toString())
							.student(st)
							.status(PaymentStatus.CREATED)
							.build();
					paymentRepository.save(payment);
				}
			});
		};
	}
}
