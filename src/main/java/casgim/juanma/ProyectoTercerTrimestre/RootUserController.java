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
 * Esta clase sirve para controlar la ventana rootuser.fxml
 *
 */
public class RootUserController {
	UserDAO user = new UserDAO();
	int id_u_aux = DataService.useraux.getId_user();
	
	@FXML
	private TableView<User> myUser;
	@FXML
	private TableColumn<Subcription, String> nick;
	@FXML
	private TableColumn<Subcription, Float> mail;
	@FXML
	private ObservableList<Subcription> user_list;

	
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
    	newStage.show();
    	
    	newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				// TODO Auto-generated method stub
				Platform.exit();
			}
		});
    }
    
    @FXML
    private void switchToModUser(ActionEvent event) throws IOException {
    	User u = this.myUser.getSelectionModel().getSelectedItem();
    	
    	if(u==null) {
    		JOptionPane.showMessageDialog(null, "Debes seleccionar un usuario primero");
    	} else {
    		try {
    			((Node) (event.getSource())).getScene().getWindow().hide();
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("moduser.fxml"));
    			Parent root = loader.load();
    			RootModUserController rootmoduser = loader.getController();
    			rootmoduser.initAttributes(u);
    			
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
	@FXML
	protected void initialize() {
		user_list = FXCollections.observableArrayList();
		this.mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
		this.nick.setCellValueFactory(new PropertyValueFactory<>("nick"));
		List<User> list = user.getAllUser();
		this.myUser.setItems((ObservableList<User>) list);
		
	}

}
