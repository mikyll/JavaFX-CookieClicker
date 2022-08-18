package controller;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControllerMenu {
	private final String COOKIE_FILE_TYPE = ".png";
	private final String SOUNDS_FILE_TYPE = ".mp3";
	
	@FXML private AnchorPane base;
	@FXML private VBox vboxBase;
	@FXML private VBox vboxSettings;
	@FXML private VBox vboxBack;
	
	@FXML private ComboBox<String> comboBoxCookie;
	@FXML private ComboBox<String> comboBoxSound;
	
	public void initialize() {
		ArrayList<String> listCookies;
		ArrayList<String> listSounds;
		
		try {
			listCookies = listFilesForFolder(new File(getClass().getResource("/resources/images/").toURI()));
			listSounds = listFilesForFolder(new File(getClass().getResource("/resources/sounds/").toURI()));
			
			comboBoxCookie.setItems(FXCollections.observableArrayList(listCookies));
			//comboBoxCookie.setValue(comboBoxCookie.getItems().get(0));
			comboBoxCookie.setValue("cookie");
			comboBoxSound.setItems(FXCollections.observableArrayList(listSounds));
			//comboBoxSound.setValue(comboBoxSound.getItems().get(0));
			comboBoxSound.setValue("mouseClick1");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			this.vboxSettings.setDisable(true);
		}
		
		this.vboxBase.setVisible(true);
		
		this.vboxSettings.setVisible(false);
		this.vboxBack.setVisible(false);
	}
	
	@FXML private void showSettings(ActionEvent event) {
		this.vboxBase.setVisible(false);
		
		this.vboxSettings.setVisible(true);
		this.vboxBack.setVisible(true);
	}
	
	@FXML private void goBack(ActionEvent event) {
		this.vboxSettings.setVisible(false);
		this.vboxBack.setVisible(false);
		
		this.vboxBase.setVisible(true);
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
					this.comboBoxSound.getValue() + "_released" + SOUNDS_FILE_TYPE,
					this.comboBoxSound.getValue() + "_pressed" + SOUNDS_FILE_TYPE);
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
