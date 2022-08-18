package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/ViewMenu.fxml"));
			AnchorPane baseMenu = (AnchorPane) loader.load();
			Scene scene = new Scene(baseMenu);
			scene.getStylesheets().add(Main.class.getResource("/application/application.css").toExternalForm());
			stage.setTitle("Cookie Clicker");
			stage.getIcons().add(new Image(Main.class.getResource("/resources/icons/icon_app.png").toExternalForm()));
			stage.setScene(scene);
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    public static void main(String args[]) {
    	launch(args);
    }
}