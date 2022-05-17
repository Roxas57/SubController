package casgim.juanma.ProyectoTercerTrimestre;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import casgim.juanma.ProyectoTercerTrimestre.model.DAO.SubcriptionDAO;
import casgim.juanma.ProyectoTercerTrimestre.model.DataObject.Subcription;
import casgim.juanma.ProyectoTercerTrimestre.utils.Connect;
import casgim.juanma.ProyectoTercerTrimestre.utils.methods;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ModSubController implements Initializable{
	private Connection miConexion;
	SubcriptionDAO subdao = new SubcriptionDAO();
	Subcription subcripcion = new Subcription();
	int id_u_aux = DataService.useraux.getId_user();
	int id_sub_aux = DataService.subaux.getId_sub();
	
	@FXML
	private TextField service;
	@FXML
	private DatePicker payDay;
	@FXML
	private ChoiceBox<String> ChB;
	@FXML
	private TextField price;
	
	private String[] sub = {"Diaria","Semanal","Mensual","Anual"};

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ChB.setValue("Diaria");
		ChB.getItems().addAll(sub);
		
	}
	
    @FXML
    private void switchToConfDel() throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("confdel.fxml"));
    	Scene scene = new Scene(root,300,179);
    	Stage newStage = new Stage();
    	newStage.setScene(scene);
    	newStage.show();
    	
    	if (DataService.delete!=false) {
    		
    	}
    }
    
    public void initAttributes(Subcription s) {
    	this.subcripcion = s;
    	this.service.setText(s.getService()+"");
    }
    
    @FXML
    private void saveNewSub(ActionEvent event) throws IOException {
    	try {
			this.miConexion = Connect.getConnect();
			Subcription subcripcion_aux = new Subcription();
			String plataforma = service.getText();
			LocalDateTime diapago = this.payDay.getValue().atStartOfDay();
			String tipopago = ChB.getValue();
			String precio = price.getText();
			float fprecio = Float.parseFloat(precio);
			
			subcripcion_aux.setId_sub(id_sub_aux);
			System.out.println(subcripcion_aux.getId_sub());
			subcripcion_aux.setService(plataforma);
			System.out.println(subcripcion_aux.getService());
			subcripcion_aux.setPay_day(diapago);
			System.out.println(subcripcion_aux.getPay_day());
			subcripcion_aux.setType(tipopago);
			System.out.println(subcripcion_aux.getType());
			subcripcion_aux.setPrice(fprecio);
			System.out.println(subcripcion_aux.getPrice());
			subcripcion_aux.setId_user(id_u_aux);
			System.out.println(subcripcion_aux.getId_user());
			
			boolean result = false;
			result = subdao.update(subcripcion_aux);
			
			if(result!=false) {
				JOptionPane.showMessageDialog(null, "Subcripción registrada con éxito");
				switchToSubcription(event);
			} else {
	    		JOptionPane.showMessageDialog(null, "Error al modificar la subcripción");
	    	}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
    }
    
    @FXML
    private void switchToSubcription(ActionEvent event) throws IOException {
    	((Node) (event.getSource())).getScene().getWindow().hide();
    	Parent root = FXMLLoader.load(getClass().getResource("subcription.fxml"));
    	Scene scene = new Scene(root,600,400);
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
}
