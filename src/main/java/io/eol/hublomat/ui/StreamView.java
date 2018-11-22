package io.eol.hublomat.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;



/**
 * Main view for the stream ui.
 */
@Route ("")
public class StreamView extends VerticalLayout {

	public StreamView() {
		Button button = new Button("Click me",
				event -> Notification.show("Clicked!"));
		add(button);
	}
}
