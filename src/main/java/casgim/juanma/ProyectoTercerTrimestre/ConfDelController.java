package casgim.juanma.ProyectoTercerTrimestre;

import java.io.IOException;

import javax.swing.JOptionPane;

import casgim.juanma.ProyectoTercerTrimestre.model.DAO.SubcriptionDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * 
 * @author Juan María Castillo Giménez
 * 
 * Esta clase sirve para controlar la ventana confdel.fxml
 *
 */
public class ConfDelController {
	SubcriptionDAO subdao = new SubcriptionDAO();
	int id_sub_aux = DataService.subaux.getId_sub();
	boolean del = false;
	
	/*
	 * Este metodo recibe una id de una subcripcion a borrar
	 * si se pulsa el boton eliminar borra la subcripcion indicada
	 */
	@FXML
	private void deleteSub(ActionEvent event) throws IOException  {
		boolean result = false;
		result = subdao.delById(id_sub_aux);
		
		if(result!=false) {
			JOptionPane.showMessageDialog(null, "Subcripción eliminada con éxito");
			switchToSubcription(event);
		} else {
    		JOptionPane.showMessageDialog(null, "Error al eliminar la subcripción");
    	}
	}
	
	/*
	 * Este metodo cambia a la ventana subcription.fxml
	 */
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