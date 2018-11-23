package io.eol.hublomat.ui;

import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.eol.hublomat.ui.adapter.messaging.kinesis.KinesisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;


@SpringBootApplication
public class StreamUIApplication extends SpringBootServletInitializer {

	@Autowired
	private KinesisProperties kinesisProperties;

	public static void main(String[] args) {
		SpringApplication.run(StreamUIApplication.class, args);
	}

	@Bean
	public AmazonKinesis kinesisClient() {
		AmazonKinesisClientBuilder clientBuilder = AmazonKinesisClientBuilder.standard();
		clientBuilder.withRegion(kinesisProperties.getRegion());
		AmazonKinesis client = clientBuilder.build();
		return client;
	}

	@Primary
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

		return objectMapper;
	}

}
