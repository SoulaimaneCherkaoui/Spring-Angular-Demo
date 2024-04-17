package com.patient.angularspringdemo;

import com.patient.angularspringdemo.entities.Payment;
import com.patient.angularspringdemo.entities.PaymentStatus;
import com.patient.angularspringdemo.entities.PaymentType;
import com.patient.angularspringdemo.entities.Student;
import com.patient.angularspringdemo.repository.PaymentRepo;
import com.patient.angularspringdemo.repository.StudentRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class AngularSpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AngularSpringDemoApplication.class, args);
    }
@Bean
    CommandLineRunner commandLineRunner(StudentRepo studentRepo, PaymentRepo paymentRepo){
        return args -> {
          studentRepo.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Soulaimne").code("112233").programId("BDCC")
                  .build());
            studentRepo.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Zaid").code("112244").programId("BDCC")
                    .build());
            studentRepo.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Nouhayla").code("112255").programId("BDCC")
                    .build());
            studentRepo.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Ayoub").code("112266").programId("BDCC")
                    .build());
            PaymentType[] paymentTypes=PaymentType.values();
            Random random = new Random();
            studentRepo.findAll().forEach(student -> {
                for(int i=0;i<10;i++){
                    int index = random.nextInt(paymentTypes.length);
                    Payment payment = Payment.builder()
                            .amount(2000+(int)(Math.random()+2000))
                            .type(paymentTypes[index])
                            .status(PaymentStatus.CREATED)
                            .date(LocalDate.now())
                            .student(student)
                            .build();
                    paymentRepo.save(payment);

                }
            });
        };

}
}
