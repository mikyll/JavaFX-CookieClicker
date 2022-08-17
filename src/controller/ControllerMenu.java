package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControllerMenu {
	@FXML private AnchorPane base;
	@FXML private VBox vboxBase;
	@FXML private VBox vboxOpt1;
	@FXML private VBox vboxOpt2;
	@FXML private VBox vboxBack;

	public void initialize() {
		this.vboxBase.setVisible(true);
		
		this.vboxOpt1.setVisible(false);
		this.vboxOpt2.setVisible(false);
		this.vboxBack.setVisible(false);
	}
	
	@FXML private void showMenuOpt1(ActionEvent event) {
		this.vboxBase.setVisible(false);
		
		this.vboxOpt1.setVisible(true);
		this.vboxBack.setVisible(true);
	}
	
	@FXML private void showMenuOpt2(ActionEvent event) {
		this.vboxBase.setVisible(false);
		
		this.vboxOpt2.setVisible(true);
		this.vboxBack.setVisible(true);
	}
	
	@FXML private void goBack(ActionEvent event) {
		this.vboxOpt1.setVisible(false);
		this.vboxOpt2.setVisible(false);
		this.vboxBack.setVisible(false);
		
		this.vboxBase.setVisible(true);
	}
	
	@FXML private void startGame(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(ControllerMenu.class.getResource("/view/ViewGame.fxml"));
			Stage stage = (Stage) this.base.getScene().getWindow();
			AnchorPane baseGame = (AnchorPane) loader.load();
			Scene scene = new Scene(baseGame);
			scene.getStylesheets().add(ControllerMenu.class.getResource("/application/application.css").toExternalForm());
			stage.setTitle("Test");
			stage.setScene(scene);
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
