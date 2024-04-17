package com.patient.angularspringdemo.web;

import com.patient.angularspringdemo.entities.Payment;
import com.patient.angularspringdemo.entities.PaymentStatus;
import com.patient.angularspringdemo.entities.PaymentType;
import com.patient.angularspringdemo.entities.Student;
import com.patient.angularspringdemo.repository.PaymentRepo;
import com.patient.angularspringdemo.repository.StudentRepo;
import com.patient.angularspringdemo.services.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class PaymentRestController {
    private PaymentRepo paymentRepo;
    private StudentRepo studentRepo;
    private PaymentService paymentService;

    public PaymentRestController(PaymentRepo paymentRepo, StudentRepo studentRepo, PaymentService paymentService) {
        this.paymentRepo = paymentRepo;
        this.studentRepo = studentRepo;
        this.paymentService = paymentService;
    }


    @GetMapping(path="/payments")
    public List<Payment> allpayment(){
        return paymentRepo.findAll();
    }
    @GetMapping(path="/students/{code}/payments")
    public List<Payment> paymentByStudent(String code){
        return paymentRepo.findByStudentCode(code);
    }
    @GetMapping(path="/payments/status")
    public List<Payment> paymentByStatus(@RequestParam PaymentStatus status){
        return paymentRepo.findByStatus(status);
    }
    @GetMapping(path="/payments/type")
    public List<Payment> paymentByType(@RequestParam PaymentType paymentType){
        return paymentRepo.findByType(paymentType);
    }

    @GetMapping(path="/payments/{id}")
    public Payment getPaymentById(@PathVariable Long id){
        return paymentRepo.findById(id).get();
    }
    @GetMapping(path="/students")
    public List<Student> allStudents(){
        return studentRepo.findAll();
    }
    @GetMapping(path="/students/{code}")
    public Student getStudentBycode(@PathVariable  String code){
        return studentRepo.findByCode(code);

    }
    @GetMapping(path="/studentsByPogramId")
    public List<Student> getStudentBypProgramId(@RequestParam String programId){
return studentRepo.findByProgramId(programId);
    }
    @PutMapping("/payments/{id}")
    public Payment updatePaymentStatus(PaymentStatus status,Long id){
        return paymentService.updatePaymentStatus(status,id);
    }
    @PostMapping(path="/payments",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam MultipartFile file, LocalDate date, double amout, PaymentType type, String studentCode) throws IOException {
        return paymentService.savePayment(file,date,amout,type,studentCode);
    }
    @GetMapping(path="/paymentFile/{paymentId}",produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPayment(@PathVariable Long paymentId) throws IOException {
     return paymentService.getPayment(paymentId);

    }
}
