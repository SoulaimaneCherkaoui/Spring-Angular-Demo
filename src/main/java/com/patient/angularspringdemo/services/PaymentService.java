package com.patient.angularspringdemo.services;

import com.patient.angularspringdemo.entities.Payment;
import com.patient.angularspringdemo.entities.PaymentStatus;
import com.patient.angularspringdemo.entities.PaymentType;
import com.patient.angularspringdemo.entities.Student;
import com.patient.angularspringdemo.repository.PaymentRepo;
import com.patient.angularspringdemo.repository.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class PaymentService {
    private StudentRepo studentRepo;
    private PaymentRepo paymentRepo;

    public PaymentService(StudentRepo studentRepo, PaymentRepo paymentRepo) {
        this.studentRepo = studentRepo;
        this.paymentRepo = paymentRepo;
    }
    public Payment savePayment(MultipartFile file, LocalDate date, double amout, PaymentType type, String studentCode) throws IOException {
        Path folderPath = Paths.get(System.getProperty("user.home"),"enset-data","payments");
        if(Files.exists(folderPath)){
            Files.createDirectories(folderPath);
        }
        String fileName = UUID.randomUUID().toString();
        Path filePath =Paths.get(System.getProperty("user.home"),"enset-data","payments",fileName+".pdf");
        //enrigstrer le fichier dans le path
        Files.copy(file.getInputStream(),filePath);
        Student student = studentRepo.findByCode(studentCode);
        Payment payment = Payment.builder()
                .date(date)
                .type(type)
                .student(student)
                .amount(amout)
                .file(String.valueOf(filePath.toUri()))
                .build();
        return paymentRepo.save(payment);
    }
    public byte[] getPayment( Long paymentId) throws IOException {
        Payment payment = paymentRepo.findById(paymentId).get();
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));

    }
    public Payment updatePaymentStatus(PaymentStatus status, Long id){
        Payment payment = paymentRepo.findById(id).get();
        payment.setStatus(status);
        return paymentRepo.save(payment);
    }
}
