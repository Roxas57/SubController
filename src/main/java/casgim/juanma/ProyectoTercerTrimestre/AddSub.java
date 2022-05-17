package casgim.juanma.ProyectoTercerTrimestre;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class AddSub implements Initializable{
	private Connection miConexion;
	SubcriptionDAO subdao = new SubcriptionDAO();
	int id_u_aux = DataService.useraux.getId_user();
	
	@FXML
	private TextField service;
	@FXML
	private DatePicker payDay;
	@FXML
	private ChoiceBox<String> ChB;
	@FXML
	private TextField price;
	
	private String[] sub = {"Diaria","Semanal","Mensual","Anual"};
	
	@FXML
	private void addButton(ActionEvent event) throws IOException {
		try {
			this.miConexion = Connect.getConnect();
			Subcription sub = new Subcription();
			String plataforma = service.getText();
			LocalDateTime diapago = this.payDay.getValue().atStartOfDay();
			String tipopago = ChB.getValue();
			String precio = price.getText();
			float fprecio = Float.parseFloat(precio);
			
			int id=methods.generaAleatorio(0, 10000);
			
			sub.setId_sub(id);
			sub.setService(plataforma);
			sub.setPay_day(diapago);
			sub.setType(tipopago);
			sub.setPrice(fprecio);
			sub.setId_user(id_u_aux);
			
			boolean result = false;
			result = subdao.insert(sub);
			
			if(result!=false) {
				JOptionPane.showMessageDialog(null, "Subcripción registrada con éxito");
				switchToSubcription(event);
			} else {
	    		JOptionPane.showMessageDialog(null, "Error al crear Subcripción");
	    	}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error al crear Subcripción");
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ChB.setValue("Diaria");
		ChB.getItems().addAll(sub);
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