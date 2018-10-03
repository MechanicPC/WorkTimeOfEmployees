package controller;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.*;
import model.Employee;
import model.DataModel;

public class MainWindowController {
	@SuppressWarnings("unused")
	private Main main;
	private Stage primaryStage;
	final FileChooser fileChooser = new FileChooser();
	private File txtFile = null;
	private File txtFileReport = null;
	private String firstName;
	private String lastName;
	private int room;
	private int startTime;
	private int endTime;
	private int workingTime;
	DataModel dataModel = new DataModel();
	
    
	@FXML private Button buttonOpen;
	@FXML private Button buttonSave;
	@FXML private Button buttonAdd;
	@FXML private Button buttonReport;
	@FXML private ImageView imageViewRoom;
	@FXML private TextField textFieldFirstName;
	@FXML private TextField textFieldLastName;
	@FXML private TextField textFieldRoomNumber;
	@FXML private TextField textFieldStartTime;
	@FXML private TextField textFieldEndTime;
	@FXML private TableView<Employee> tableData;
	@FXML private TableColumn<Employee, String> tableColumnFirstName;
	@FXML private TableColumn<Employee, String> tableColumnLastName;
	@FXML private TableColumn<Employee, Integer> tableColumnRoom;

	@FXML
	public void closeStage() {
		primaryStage.close();
	}
	
	@FXML
	void onActionButtonOpen(ActionEvent event) {
		txtFile = fileChooser.showOpenDialog(null);
		dataModel.openFile(txtFile);
		tableData.setItems(dataModel.getEmployeesList());
	}

	@FXML
	void onActionButtonAdd(ActionEvent event) {
		if (textFieldFirstName.getText().trim().isEmpty() || textFieldFirstName.getText().trim().isEmpty()
				|| textFieldRoomNumber.getText().trim().isEmpty() || textFieldStartTime.getText().trim().isEmpty()
				|| textFieldEndTime.getText().trim().isEmpty()){
			textFieldFirstName.setText("Uzupełnij wszystkie pola");
		}
		else {
			firstName = textFieldFirstName.getText();
			lastName = textFieldLastName.getText();
			room = Integer.parseInt(textFieldRoomNumber.getText());
			startTime = Integer.parseInt(textFieldStartTime.getText());
			endTime = Integer.parseInt(textFieldEndTime.getText());
			workingTime = endTime - startTime;
			dataModel.addEmployeeToEmployeesList(firstName, lastName, room, startTime, endTime, workingTime);
		}
	}

	@FXML
	void onActionButtonReport(ActionEvent event) {
		txtFileReport = fileChooser.showSaveDialog(null);
		dataModel.report(txtFileReport);
	}

	@FXML
	void onActionButtonSave(ActionEvent event) {
		dataModel.saveFile(txtFile);
	}

	public void setMain(Main main, Stage primaryStage) {
		this.main = main;
		this.primaryStage = primaryStage;
	}
	
	private static void configureFileChooser(final FileChooser fileChooser) {
		fileChooser.setTitle("Open / Save text file");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text file ", "*.txt"));
		fileChooser.setInitialDirectory(new File("."));
	}

	@FXML
	public void initialize() {
		configureFileChooser(fileChooser);
		
		tableColumnFirstName.prefWidthProperty().bind(tableData.widthProperty().multiply(0.4));
		tableColumnLastName.prefWidthProperty().bind(tableData.widthProperty().multiply(0.4));
		tableColumnRoom.prefWidthProperty().bind(tableData.widthProperty().multiply(0.2));
		
		tableColumnFirstName.setCellValueFactory(
				new PropertyValueFactory<Employee, String>("firstName")
				);
		tableColumnLastName.setCellValueFactory(
				new PropertyValueFactory<Employee, String>("lastName")
				);
		tableColumnRoom.setCellValueFactory(
				new PropertyValueFactory<Employee, Integer>("room")
				);
		tableData.getSelectionModel().selectedItemProperty().addListener(
				(ov, oldVal, newVal) ->
				{
					textFieldFirstName.setText(newVal.getFirstName());
					textFieldLastName.setText(newVal.getLastName());
					textFieldRoomNumber.setText(String.valueOf(newVal.getRoom()));
					textFieldStartTime.setText(String.valueOf(newVal.getStartTime()));
					textFieldEndTime.setText(String.valueOf(newVal.getEndTime()));
					if (newVal.getRoom() >= 101 && newVal.getRoom() <= 107) {
						imageViewRoom.setImage(new Image(getClass().
							getResourceAsStream("/model/scheme_room_" + String.valueOf(newVal.getRoom()) + ".png")));
					}
					else {imageViewRoom.setImage(new Image(getClass().
							getResourceAsStream("/model/scheme_room_.png")));
					}
				});
	}
}