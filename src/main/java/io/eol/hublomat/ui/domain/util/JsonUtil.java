package io.eol.hublomat.ui.domain.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.eol.hublomat.ui.domain.entity.AnyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JsonUtil {

	private final ObjectMapper objectMapper;

	@Autowired
	public JsonUtil(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public byte[] anyMessageToJsonAsBytes(AnyMessage anyMessage) {
		try {
			return objectMapper.writeValueAsBytes(anyMessage);
		} catch (IOException e) {
			return null;
		}
	}

	public AnyMessage anyMessageFromJsonAsBytes(byte[] bytes) {
		try {
			return objectMapper.readValue(bytes, AnyMessage.class);
		} catch (IOException e) {
			return null;
		}
	}
}
