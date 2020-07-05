package com.learn.springbootwithgraphql.service.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.learn.springbootwithgraphql.model.Payment;
import com.learn.springbootwithgraphql.repository.PaymentRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllPaymentsDataFetcher implements DataFetcher<List<Payment>>{
	
    @Autowired
	PaymentRepository paymentRepository;
	
	@Override
	public List<Payment> get(DataFetchingEnvironment environment) {
	
		return paymentRepository.findAll();
	
	}

}
