package com.learn.springbootwithgraphql.service.datafetcher;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.learn.springbootwithgraphql.model.Payment;
import com.learn.springbootwithgraphql.repository.PaymentRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class PaymentDataFetcher implements DataFetcher<Payment> {

	private static final Logger LOGGER = LogManager.getLogger(PaymentDataFetcher.class.getName());
	
	@Autowired
	PaymentRepository paymentRepository;

	@Override
	public Payment get(DataFetchingEnvironment environment) {
	
		LOGGER.info("Get Payment");
		String paymentType= environment.getArgument("id");
		Optional<Payment> payment= paymentRepository.findById(paymentType);
		
		return payment.get();
	}

}
