package io.eol.hublomat.ui;

import java.util.stream.IntStream;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import io.eol.hublomat.ui.adapter.storage.database.AnyMessageRepository;
import io.eol.hublomat.ui.domain.entity.AnyMessage;
import io.eol.hublomat.ui.domain.util.AnyMessageGenerator;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Main view for the stream ui.
 */
@Route("")
@Push
public class StreamView extends VerticalLayout {

	private final AnyMessageRepository anyMessageRepository;
	final Grid<AnyMessage> grid;

	@Autowired
	public StreamView(AnyMessageRepository anyMessageRepository) {
		this.anyMessageRepository = anyMessageRepository;

		Label label = new Label("Simple Kinesis Stream View");
		add(label);

		this.setSizeFull();
		this.grid = new Grid<>();
		configureGrid(grid);
		this.add(grid);

		listMessages();
	}

	private void configureGrid(Grid<AnyMessage> grid) {
		grid.addColumn(AnyMessage::getAnyId).setHeader("Id");
		grid.addColumn(AnyMessage::getAnyString).setHeader("Info");
		grid.addColumn(AnyMessage::getCreated).setHeader("Created At");
		grid.setSizeFull();
		this.add(grid);
	}

	private void configureDataProvider(Grid<AnyMessage> grid) {
		AnyMessageGenerator generator = new AnyMessageGenerator();
		grid.setDataProvider(DataProvider.fromCallbacks(
				query -> IntStream
						.range(query.getOffset(),
								query.getOffset() + query.getLimit())
						.mapToObj(index -> generator.createAnyMessage(index + 1)),
				query -> 100 * 1000 * 1000));
	}

	private void listMessages() {
		grid.setItems(anyMessageRepository.findAll().stream());
	}
}
