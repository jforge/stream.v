package io.eol.hublomat.ui.application.consumer;

import io.eol.hublomat.ui.adapter.messaging.kinesis.KinesisProperties;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StreamDataConsumerCamel extends RouteBuilder {

	private final KinesisProperties kinesisProperties;

	@Autowired
	public StreamDataConsumerCamel(KinesisProperties kinesisProperties) {
		this.kinesisProperties = kinesisProperties;
	}

	@Override
	public void configure() {
		from(kinesisProperties.getCamelUrl())
				.bean("recorder", "store")
				.to("log:out?showAll=true");
	}
}
