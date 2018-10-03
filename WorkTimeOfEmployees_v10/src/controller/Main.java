package controller;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader =
					new FXMLLoader(
						Main.class.getResource("/view/MainWindowView.fxml"));
			AnchorPane anchorPane = loader.load();
			
			Scene scene = new Scene(anchorPane);
			
			MainWindowController mainWindowController = loader.getController();
			mainWindowController.setMain(this, primaryStage);
			primaryStage.setMinWidth(1280.0);
			primaryStage.setMinHeight(800.0);
			primaryStage.setMaxWidth(1280.0);
			primaryStage.setMaxHeight(800.0);
			primaryStage.setTitle("Baza danych czasu pracy pracowników");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}