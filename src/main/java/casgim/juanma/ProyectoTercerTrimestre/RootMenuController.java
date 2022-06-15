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
 *         Esta clase sirve para controlar la ventana confdel.fxml
 *
 */
public class RootMenuController {
	SubcriptionDAO subdao = new SubcriptionDAO();
	int id_sub_aux = DataService.subaux.getId_sub();
	boolean del = false;

	/*
	 * Este metodo cambia a la ventana rootsubcription.fxml
	 */
	@FXML
	private void switchToAllSubs(ActionEvent event) throws IOException {
		((Node) (event.getSource())).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("rootsubcription.fxml"));
		Scene scene = new Scene(root, 600, 400);
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
	 * Este metodo cambia a la ventana rootuser.fxml
	 */
	@FXML
	private void switchToAllUsers(ActionEvent event) throws IOException {
		((Node) (event.getSource())).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("rootuser.fxml"));
		Scene scene = new Scene(root, 600, 400);
		Stage newStage = new Stage();
		newStage.setScene(scene);
		newStage.setTitle("All Users");
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
	private void switchToLogin(ActionEvent event) throws IOException {
		((Node) (event.getSource())).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene scene = new Scene(root, 300, 430);
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
}