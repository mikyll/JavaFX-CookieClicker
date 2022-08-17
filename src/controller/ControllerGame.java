package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.Counter;

public class ControllerGame {
	@FXML private Label labelClicks;
	@FXML private Button buttonClick;
	
	private Counter counter;
	
	public void initialize() {
		counter = new Counter();
		
		labelClicks.setText("" + 0);
	}
	
	@FXML private void pressCookie(MouseEvent event) {
		buttonClick.setId("cookiePressed");
	}
	@FXML private void releaseCookie(MouseEvent event) {
		buttonClick.setId("cookie");
		
		counter.increase();
		labelClicks.setText("" + counter.getValue());
	}
}
