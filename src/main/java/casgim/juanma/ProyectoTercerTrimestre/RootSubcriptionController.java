package casgim.juanma.ProyectoTercerTrimestre;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import casgim.juanma.ProyectoTercerTrimestre.model.DAO.SubcriptionDAO;
import casgim.juanma.ProyectoTercerTrimestre.model.DAO.UserDAO;
import casgim.juanma.ProyectoTercerTrimestre.model.DataObject.Subcription;
import casgim.juanma.ProyectoTercerTrimestre.model.DataObject.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * 
 * @author Juan María Castillo Giménez
 * 
 * Esta clase sirve para controlar la ventana subcription.fxml
 *
 */
public class RootSubcriptionController {
	SubcriptionDAO sub = new SubcriptionDAO();
	int id_u_aux = DataService.useraux.getId_user();
	
    @FXML
    private Label annual_expend;
	@FXML
	private TableView<Subcription> mySubcription;
	@FXML
	private TableColumn<Subcription, String> user;
	@FXML
	private TableColumn<Subcription, String> service;
	@FXML
	private TableColumn<Subcription, Float> price;
	@FXML
	private TableColumn<Subcription, LocalDateTime> payDay;
	@FXML
	private TableColumn<Subcription, String> type;
	@FXML
	private ObservableList<Subcription> subcripcion;
	
	/*
	 * Este metodo cambia a la ventana rootmenu.fxml
	 */
    @FXML
    private void switchToRootMenu(ActionEvent event) throws IOException {
    	((Node) (event.getSource())).getScene().getWindow().hide();
    	Parent root = FXMLLoader.load(getClass().getResource("rootmenu.fxml"));
    	Scene scene = new Scene(root,400,200);
    	Stage newStage = new Stage();
    	newStage.setScene(scene);
    	newStage.setTitle("Your Subcriptions");
    	newStage.show();
    	
    	newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				// TODO Auto-generated method stub
				Platform.exit();
			}
		});
    }
    
    /*
	 * Este metodo cambia a la ventana modsub.fxml cuando hemos seleccionado
	 * una subcripcion a modificar
	 */
    @FXML
    private void switchToModSub(ActionEvent event) throws IOException {
    	Subcription sub = this.mySubcription.getSelectionModel().getSelectedItem();
    	DataService.subaux = sub;

    	if(sub==null) {
    		JOptionPane.showMessageDialog(null, "Debes seleccionar una subcripción primero");
    	} else {
    		try {
    			((Node) (event.getSource())).getScene().getWindow().hide();
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("modsub.fxml"));
    			Parent root = loader.load();
    			ModSubController modsub = loader.getController();
    			modsub.initAttributes(sub);
    			
    	    	Scene scene = new Scene(root,300,430);
    	    	Stage newStage = new Stage();
    	    	newStage.setScene(scene);
    	    	newStage.initModality(Modality.APPLICATION_MODAL);
    	    	newStage.show();
    	    	
    	    	newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
    				
    				@Override
    				public void handle(WindowEvent event) {
    					// TODO Auto-generated method stub
    					Platform.exit();
    				}
    			});
    		} catch (Exception ex) {
    			JOptionPane.showMessageDialog(null, ex.getMessage());
			}
    	}	
    }
    
    /*
	 * Este metodo inicializa el campo TableView con las subcripciones
	 * de un usuario almacenadas en una base de datos
	 */
	@FXML
	protected void initialize() {
		subcripcion = FXCollections.observableArrayList();
		this.user.setCellValueFactory(new PropertyValueFactory<>("user"));
		this.service.setCellValueFactory(new PropertyValueFactory<>("service")); 
		this.price.setCellValueFactory(new PropertyValueFactory<>("price"));
		this.payDay.setCellValueFactory(new PropertyValueFactory<>("pay_day"));
		this.type.setCellValueFactory(new PropertyValueFactory<>("type"));
		ObservableList<Subcription> list = (ObservableList<Subcription>) sub.getAllSubcription();
		this.mySubcription.setItems(list);
	}

}
