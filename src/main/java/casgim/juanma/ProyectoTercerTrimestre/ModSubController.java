package casgim.juanma.ProyectoTercerTrimestre;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.IsoChronology;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import casgim.juanma.ProyectoTercerTrimestre.model.DAO.SubcriptionDAO;
import casgim.juanma.ProyectoTercerTrimestre.model.DataObject.Subcription;
import casgim.juanma.ProyectoTercerTrimestre.model.DataObject.User;
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
	User u_aux = DataService.useraux;
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

	/*
	 * Este metodo sirve para cargar un array en el objeto
	 * ChB (ChoiceBox) e indica en el mismo la opción "Diaria"
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ChB.setValue("Diaria");
		ChB.getItems().addAll(sub);
		
	}
	
	/*
	 * Este metodo cambia a la ventana confdel.fxml
	 */
    @FXML
    private void switchToConfDel(ActionEvent event) throws IOException {
    	((Node) (event.getSource())).getScene().getWindow().hide();

    	Parent root = FXMLLoader.load(getClass().getResource("confdel.fxml"));
    	Scene scene = new Scene(root,300,179);
    	Stage newStage = new Stage();
    	newStage.setScene(scene);
    	newStage.show();
    	
    }
    
    public void initAttributes(Subcription s) {
    	this.subcripcion = s;
    	this.service.setText(s.getService()+"");
    	
    	this.payDay.setValue(s.getPay_day().toLocalDate());
    	
    	String price = String.valueOf(s.getPrice());
    	this.price.setText(price);
    	
    	if (s.getType().equals("Diaria")) {
    		ChB.setValue("Diaria");
    	} else if(s.getType().equals("Semanal")) {
    		ChB.setValue("Semanal");
    	} else if(s.getType().equals("Mensual")) {
    		ChB.setValue("Mensual");
    	} else if(s.getType().equals("Anual")) {
    		ChB.setValue("Anual");
    	}
    }
    
    /*
	 * Este metodo sirve para guardar los cambios que vayamos
	 * a realizar en una subcripcion
	 */
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
			subcripcion_aux.setService(plataforma);
			subcripcion_aux.setPay_day(diapago);
			subcripcion_aux.setType(tipopago);
			subcripcion_aux.setPrice(fprecio);
			subcripcion_aux.setUsuario(u_aux);;
			
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
    
    /*
	 * Este metodo cambia a la ventana subcription.fxml
	 */
    @FXML
    private void switchToSubcription(ActionEvent event) throws IOException {
    	((Node) (event.getSource())).getScene().getWindow().hide();
    	if (u_aux.getId_user()!=0) {
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
    	} else {
    		Parent root = FXMLLoader.load(getClass().getResource("rootsubcription.fxml"));
    		Scene scene = new Scene(root,600,400);
        	Stage newStage = new Stage();
        	newStage.setScene(scene);
        	newStage.setTitle("ALL Subcriptions");
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
}
