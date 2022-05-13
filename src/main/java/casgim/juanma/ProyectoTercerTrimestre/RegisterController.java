package casgim.juanma.ProyectoTercerTrimestre;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import casgim.juanma.ProyectoTercerTrimestre.model.DAO.UserDAO;
import casgim.juanma.ProyectoTercerTrimestre.model.DataObject.User;
import casgim.juanma.ProyectoTercerTrimestre.utils.Connect;
import casgim.juanma.ProyectoTercerTrimestre.utils.methods;
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

public class RegisterController {
	private Connection miConexion;
	private PreparedStatement sentencia;
	private ResultSet resultado;
	UserDAO udao = new UserDAO();
	
	@FXML
	private TextField mail;
	
	@FXML
	private TextField user;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private void registerButton(ActionEvent event) throws IOException {
		try {
			this.miConexion = Connect.getConnect();
			User u = new User();
			Pattern pat= Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
			Matcher mat= pat.matcher(mail.getText());
			Pattern pat2= Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=?])(?=\\S+$).{8,}$");
			Matcher mat2= pat2.matcher(password.getText());
			
			if(mat.matches()&&mat2.matches()) {
				String correo = mail.getText();
				String usuario = user.getText();
		    	String pass = password.getText();
		    	
		    	int id=methods.generaAleatorio(2, 100);
				/*
				do {
					id = methods.generaAleatorio(2, 100);
					u = udao.get(id);
				} while (u!=null);
				*/
				u.setId_user(id);
		    	u.setMail(correo);
		    	u.setNick(usuario);
		    	u.setPassword(pass);
		    	
		    	boolean result = false;
		    	result = udao.insert(u);	
		    	
		    	if (result!=false) {
		    		JOptionPane.showMessageDialog(null, "Usuario registrado con éxito");
		    		DataService.useraux.setId_user(id);
		    		DataService.useraux.setMail(correo);
		    		DataService.useraux.setNick(usuario);
		    		DataService.useraux.setPassword(pass);
			    	switchToSubcription(event);
		    	} else {
		    		JOptionPane.showMessageDialog(null, "Error al crear usuario");
		    	}
			} else {
				JOptionPane.showMessageDialog(null, "Error");
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