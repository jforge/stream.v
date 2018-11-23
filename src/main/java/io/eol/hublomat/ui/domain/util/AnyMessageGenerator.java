package io.eol.hublomat.ui.domain.util;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;

import io.eol.hublomat.ui.domain.entity.AnyMessage;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AnyMessageGenerator {

	public AnyMessage createAnyMessage(int index) {
		AnyMessage anyMessage = new AnyMessage(UUID.randomUUID(), String.format("message number: %d", index), Date.from(OffsetDateTime.now().toInstant()));
		return anyMessage;
	}
}
