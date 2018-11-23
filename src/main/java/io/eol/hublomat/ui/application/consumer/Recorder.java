package io.eol.hublomat.ui.application.consumer;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Resource;

import com.amazonaws.services.kinesis.model.Record;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.eol.hublomat.ui.adapter.storage.database.AnyMessageRepository;
import io.eol.hublomat.ui.domain.entity.AnyMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Recorder {

	@Autowired
	private ObjectMapper objectMapper;

	@Resource
	private AnyMessageRepository anyMessageRepository;

	public Optional<AnyMessage> store(Exchange ex) {
		try {
			AnyMessage anyMessage = objectMapper.readValue(ex.getIn(Message.class)
							.getBody(Record.class)
							.getData()
							.array(),
					AnyMessage.class);

			if (!Objects.isNull(anyMessage)) {
				anyMessageRepository.save(anyMessage);
			}

			return Optional.of(anyMessage);
		} catch (IOException e) {
			log.error("Error during deserialization: {}", e.getMessage());
			return Optional.empty();
		}
	}

}
