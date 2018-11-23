package io.eol.hublomat.ui.adapter.messaging.kinesis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kinesis")
@Data
public class KinesisProperties {
	private String region;
	private String stream;
	private String client;

	public String getCamelUrl() {
		return String.format("aws-kinesis://%s?amazonKinesisClient=#%s", stream, client);
	}

}
