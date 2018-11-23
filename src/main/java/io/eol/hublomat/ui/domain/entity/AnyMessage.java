package io.eol.hublomat.ui.domain.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class AnyMessage {

	@Id
	private UUID anyId;
	private String anyString;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
	private Date created;

	@JsonCreator
	public AnyMessage(@JsonProperty("anyId") UUID anyId, @JsonProperty("anyString") String anyString, @JsonProperty("created") Date created) {
		this.anyId = anyId;
		this.anyString = anyString;
		this.created = created;
	}
}
