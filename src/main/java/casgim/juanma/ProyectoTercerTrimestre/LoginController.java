package casgim.juanma.ProyectoTercerTrimestre;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import casgim.juanma.ProyectoTercerTrimestre.model.DAO.UserDAO;
import casgim.juanma.ProyectoTercerTrimestre.model.DataObject.User;
import casgim.juanma.ProyectoTercerTrimestre.utils.Connect;
import casgim.juanma.ProyectoTercerTrimestre.utils.Methods;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * 
 * @author Juan María Castillo Giménez
 * 
 * Esta clase sirve para controlar la ventana login.fxml
 *
 */
public class LoginController {
	private Connection miConexion;
	private PreparedStatement sentencia;
	private ResultSet resultado;
	UserDAO userDao = new UserDAO();
	
	@FXML
	private TextField user;
	
	@FXML
	private PasswordField password;
	
	/*
	 * Este metodo cambia a la ventana register.fxml
	 */
    @FXML
    private void switchToRegister() throws IOException {
        App.setRoot("register");
    }
    
	/*
	 * Este metodo sirve para iniciar sesion con un usuario registrado
	 * en la base de datos.
	 */
    @FXML
    private void loginButton(ActionEvent event) throws IOException {
    	UserDAO udao = new UserDAO();
    	String usuario = user.getText();
		String pass = Methods.getSHA256(password.getText());
		User u = udao.getByIdPassword(usuario, pass);
		
		if (u!=null) {
			if (user.getText().equals("root") && password.getText().equals("root")) {
				switchToRootMenu(event);
			} else {
				switchToSubcription(event);
			}
		} else {
    		JOptionPane.showMessageDialog(null, "Usuario o contraseña erróneos");
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
}