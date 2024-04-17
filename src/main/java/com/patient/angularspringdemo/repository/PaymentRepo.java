package com.patient.angularspringdemo.repository;

import com.patient.angularspringdemo.entities.Payment;
import com.patient.angularspringdemo.entities.PaymentStatus;
import com.patient.angularspringdemo.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepo extends JpaRepository<Payment,Long> {
List<Payment> findByStudentCode(String code);
List<Payment> findByStatus(PaymentStatus status);
List<Payment> findByType(PaymentType type);
}
