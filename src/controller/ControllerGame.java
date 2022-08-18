package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import model.Counter;

public class ControllerGame {
	@FXML private Label labelClicks;
	@FXML private Button buttonClick;
	@FXML private ImageView imageViewButton;
	
	private Image imageReleased;
	private Image imagePressed;
	
	private MediaPlayer playerReleased;
	private MediaPlayer playerPressed;
	
	private boolean pressed = false;
	
	private Counter counter;
	
	public ControllerGame(Image imageReleased, Image imagePressed, Media mediaReleased, Media mediaPressed) {
		this.imageReleased = imageReleased;
		this.imagePressed = imagePressed;
		
		this.playerReleased = new MediaPlayer(mediaReleased);
		this.playerPressed = new MediaPlayer(mediaPressed);
		
		this.counter = new Counter();
	}
	
	public void initialize() {
		this.imageViewButton.setImage(this.imageReleased);
		
		this.labelClicks.setText("" + 0);
	}
	
	@FXML private void pressCookie(MouseEvent event) {
		if(!pressed)
		{
			pressed = true;
			
			this.playerPressed.seek(Duration.ZERO);
			this.playerPressed.play();
			this.imageViewButton.setImage(this.imagePressed);
		}
	}
	
	@FXML private void releaseCookie(MouseEvent event) {
		if(pressed)
		{
			pressed = false;
			
			this.playerReleased.seek(Duration.ZERO);
			this.playerReleased.play();
			this.imageViewButton.setImage(this.imageReleased);
			
			this.counter.increase();
			this.labelClicks.setText("" + counter.getValue());
		}
	}
}
