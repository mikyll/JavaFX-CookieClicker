package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import model.Counter;

public class ControllerGame {
	@FXML private Label labelClicks;
	@FXML private Button buttonClick;
	
	private MediaPlayer playerPress;
	private MediaPlayer playerRelease;
	
	private Media clickPress;
	private Media clickRelease;
	
	private boolean pressed = false;
	
	private Counter counter;
	
	public ControllerGame(String soundTrackReleased, String soundTrackPressed) {
		this.clickPress = new Media(getClass().getResource("/resources/sounds/" + soundTrackReleased).toExternalForm());
		this.clickRelease = new Media(getClass().getResource("/resources/sounds/" + soundTrackPressed).toExternalForm());
		this.playerPress = new MediaPlayer(clickPress);
		this.playerRelease = new MediaPlayer(clickRelease);
		
		this.counter = new Counter();
	}
	
	public void initialize() {
		this.labelClicks.setText("" + 0);
	}
	
	@FXML private void pressCookie(MouseEvent event) {
		if(!pressed)
		{
			pressed = true;
			
			this.playerPress.seek(Duration.ZERO);
			this.playerPress.play();
			this.buttonClick.setId("cookiePressed");
		}
	}
	
	@FXML private void releaseCookie(MouseEvent event) {
		if(pressed)
		{
			pressed = false
					;
			this.playerRelease.seek(Duration.ZERO);
			this.playerRelease.play();
			this.buttonClick.setId("cookie");
			
			this.counter.increase();
			this.labelClicks.setText("" + counter.getValue());
		}
	}
}
