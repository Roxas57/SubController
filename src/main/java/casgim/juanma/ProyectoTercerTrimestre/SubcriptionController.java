package casgim.juanma.ProyectoTercerTrimestre;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import casgim.juanma.ProyectoTercerTrimestre.model.DAO.SubcriptionDAO;
import casgim.juanma.ProyectoTercerTrimestre.model.DAO.UserDAO;
import casgim.juanma.ProyectoTercerTrimestre.model.DataObject.Subcription;
import casgim.juanma.ProyectoTercerTrimestre.model.DataObject.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SubcriptionController {
	SubcriptionDAO sub = new SubcriptionDAO();
	int id_u_aux = DataService.useraux.getId_user();
	
    @FXML
    private void switchToAddSub() throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("addsub.fxml"));
    	Scene scene = new Scene(root,300,430);
    	Stage newStage = new Stage();
    	newStage.setScene(scene);
    	newStage.show();
    }
    
    @FXML
    private Label annual_expend;
	@FXML
	private TableView<Subcription> mySubcription;
	@FXML
	private TableColumn<Subcription, String> service;
	@FXML
	private TableColumn<Subcription, Float> price;
	@FXML
	private TableColumn<Subcription, Date> payDay;
	@FXML
	private TableColumn<Subcription, String> type;
	@FXML
	private TableColumn<Subcription, Date> nextDay;
	
	@FXML
	private ObservableList<Subcription> subcripcion; 
	
	
	@FXML
	protected void initialize() {
		subcripcion = FXCollections.observableArrayList();
		
		this.service.setCellValueFactory(new PropertyValueFactory<>("service")); 
		this.price.setCellValueFactory(new PropertyValueFactory<>("price"));
		this.payDay.setCellValueFactory(new PropertyValueFactory<>("pay_day"));
		this.type.setCellValueFactory(new PropertyValueFactory<>("type"));
		ObservableList<Subcription> list = sub.getAllSub(DataService.useraux);
		this.mySubcription.setItems(list);
	}

}
