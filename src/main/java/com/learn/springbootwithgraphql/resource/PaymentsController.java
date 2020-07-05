package com.learn.springbootwithgraphql.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.springbootwithgraphql.service.GraphQLService;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/rest")
public class PaymentsController {
	
	@Autowired
	GraphQLService graphQLService;
	
	@PostMapping("/payments")
	public ResponseEntity<Object> getPaymentDetails(@RequestBody String query) {
		
		ExecutionResult execute = graphQLService.getGraphQl().execute(query);
		
		return new ResponseEntity<Object>(execute, HttpStatus.OK);
	}

}
