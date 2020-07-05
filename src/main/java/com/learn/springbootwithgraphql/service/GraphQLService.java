package com.learn.springbootwithgraphql.service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.learn.springbootwithgraphql.model.Payment;
import com.learn.springbootwithgraphql.repository.PaymentRepository;
import com.learn.springbootwithgraphql.service.datafetcher.AllPaymentsDataFetcher;
import com.learn.springbootwithgraphql.service.datafetcher.PaymentDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {
	
	private static final Logger LOGGER = LogManager.getLogger(GraphQLService.class.getName());
	
	@Value("classpath:payment.graphqls")
	Resource resource;
	
	@Autowired
	PaymentRepository paymentRepsitory;
	
	@Autowired
	private AllPaymentsDataFetcher allPaymentsDataFetcher;
	
	@Autowired
	private PaymentDataFetcher paymentDataFetcher;
	
	private GraphQL graphQL;
	
	@PostConstruct
	public void loadSchema() throws IOException {
		LOGGER.info("Loading init data");
		loadDataIntoHSQL();
		
		File schemafile = resource.getFile();
		
		TypeDefinitionRegistry typeRegistry  = new SchemaParser().parse(schemafile);
		
		RuntimeWiring wiring = buildRuntimeWiring();
		
		GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		graphQL=GraphQL.newGraphQL(graphQLSchema).build();
		
	}

	private void loadDataIntoHSQL() {
	
		
		Stream.of(new Payment("A2A","ITHardwares",200L,"DKK"),
				new Payment("SEPA","Security",200L,"NOK"),
				new Payment("ITransfer","Transport",200L,"EURO")
				).forEach(payment-> {
			
			paymentRepsitory.save(payment);
		});
		
	}
	
   
	private RuntimeWiring buildRuntimeWiring() {
		
		return RuntimeWiring.newRuntimeWiring()
				.type("Query",typeWiring -> typeWiring
						.dataFetcher("allPaymentTypes", allPaymentsDataFetcher)
						.dataFetcher("payment", paymentDataFetcher))
				.build();
	}
	
	public GraphQL getGraphQl(){
		
		return graphQL;
		
	}
}
