package io.eol.hublomat.ui.adapter.storage.database;

import java.util.UUID;

import io.eol.hublomat.ui.domain.entity.AnyMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnyMessageRepository extends JpaRepository<AnyMessage, UUID> {

}
