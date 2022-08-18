package controller;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerMenu {
	private final String COOKIE_FILE_TYPE = ".png";
	private final String SOUNDS_FILE_TYPE = ".mp3";
	
	@FXML private AnchorPane base;
	@FXML private VBox vboxBase;
	@FXML private VBox vboxSettings;
	@FXML private VBox vboxBack;
	
	@FXML private ComboBox<String> comboBoxCookie;
	@FXML private ComboBox<String> comboBoxSound;
	@FXML private Slider sliderVolume;
	
	@FXML private Button buttonClick;
	@FXML private ImageView imageViewCookie;
	
	private ArrayList<String> listCookies;
	private ArrayList<String> listSounds;
	
	private Image imageReleased;
	private Image imagePressed;

	private Media mediaClickReleased;
	private Media mediaClickPressed;
	
	private MediaPlayer playerReleased;
	private MediaPlayer playerPressed;

	private boolean pressed = false;
	
	public ControllerMenu() {
		try {
			this.listCookies = listFilesForFolder(new File(getClass().getResource("/resources/images/").toURI()));
			this.listSounds = listFilesForFolder(new File(getClass().getResource("/resources/sounds/").toURI()));
		} catch (URISyntaxException e) {
			e.printStackTrace();
			this.vboxSettings.setDisable(true);
		}
	}
	
	public void initialize() {
		if (!this.vboxSettings.isDisabled()) {
			this.comboBoxCookie.setItems(FXCollections.observableArrayList(listCookies));
			this.comboBoxSound.setItems(FXCollections.observableArrayList(listSounds));
		}
		
		this.comboBoxCookie.setValue("cookie");
		this.comboBoxSound.setValue("mouseClick1");
		
		this.loadImages(this.comboBoxCookie.getValue());
		this.loadMedia(this.comboBoxSound.getValue());
		
		this.sliderVolume.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.changeVolume();
        });
		this.playerReleased.setVolume(this.sliderVolume.getValue() / 100);
		this.playerPressed.setVolume(this.sliderVolume.getValue() / 100);
		
		this.vboxBase.setVisible(true);
		
		this.vboxSettings.setVisible(false);
		this.vboxBack.setVisible(false);
	}
	
	@FXML private void goBack(ActionEvent event) {
		this.vboxSettings.setVisible(false);
		this.vboxBack.setVisible(false);
		
		this.vboxBase.setVisible(true);
	}
	
	@FXML private void showSettings(ActionEvent event) {
		this.vboxBase.setVisible(false);
		
		this.imageViewCookie.setImage(this.imageReleased);
		
		this.vboxSettings.setVisible(true);
		this.vboxBack.setVisible(true);
	}
	
	@FXML private void changeCookie() {
		this.loadImages(this.comboBoxCookie.getValue());
	}
	
	@FXML private void changeSound() {
		this.loadMedia(this.comboBoxSound.getValue());
	}
	
	@FXML private void changeVolume() {
		this.playerReleased.setVolume(this.sliderVolume.getValue() / 100);
		this.playerPressed.setVolume(this.sliderVolume.getValue() / 100);
	}
	
	@FXML private void pressCookie(MouseEvent event) {
		if(!pressed)
		{
			pressed = true;
			
			this.playerPressed.seek(Duration.ZERO);
			this.playerPressed.play();
			this.imageViewCookie.setImage(this.imagePressed);
		}
	}
	
	private void loadImages(String fileName) {
		this.imageReleased = new Image(getClass().getResource("/resources/images/" + fileName + "_released" + COOKIE_FILE_TYPE).toExternalForm());
		this.imagePressed = new Image(getClass().getResource("/resources/images/" + fileName + "_pressed" + COOKIE_FILE_TYPE).toExternalForm());
		this.imageViewCookie.setImage(this.imageReleased);
	}
	
	private void loadMedia(String fileName) {
		this.mediaClickReleased = new Media(getClass().getResource(
				"/resources/sounds/" + fileName + "_released" + SOUNDS_FILE_TYPE).toExternalForm());
		this.mediaClickPressed = new Media(getClass().getResource(
				"/resources/sounds/" + fileName + "_pressed" + SOUNDS_FILE_TYPE).toExternalForm());
		this.playerReleased = new MediaPlayer(this.mediaClickReleased);
		this.playerPressed = new MediaPlayer(this.mediaClickPressed);
	}
	
	@FXML private void releaseCookie(MouseEvent event) {
		if(pressed)
		{
			pressed = false;
			
			this.playerReleased.seek(Duration.ZERO);
			this.playerReleased.play();
			this.imageViewCookie.setImage(this.imageReleased);
		}
	}
	
	public ArrayList<String> listFilesForFolder(final File folder) {
	    ArrayList<String> result = new ArrayList<String>();
	    
		for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	        	//System.out.println(fileEntry.getName());
	        	String el = fileEntry.getName().split("_")[0];
	        	if(!result.contains(el))
	        		result.add(el);
	        }
	    }
		return result;
	}
	
	@FXML private void startGame(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(ControllerMenu.class.getResource("/view/ViewGame.fxml"));
			Stage stage = (Stage) this.base.getScene().getWindow();
			
			System.out.println(this.comboBoxSound.getValue() + "_released" + SOUNDS_FILE_TYPE);
			ControllerGame controller = new ControllerGame(
					this.imageReleased,
					this.imagePressed,
					this.mediaClickReleased,
					this.mediaClickPressed);
			loader.setController(controller);
			
			AnchorPane baseGame = (AnchorPane) loader.load();
			Scene scene = new Scene(baseGame);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			stage.setTitle("Cookie Clicker");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
