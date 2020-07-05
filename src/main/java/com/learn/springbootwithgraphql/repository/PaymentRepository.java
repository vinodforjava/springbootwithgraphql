package com.learn.springbootwithgraphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.springbootwithgraphql.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, String>{

}
