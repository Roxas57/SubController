package casgim.juanma.ProyectoTercerTrimestre;

import java.io.IOException;
import java.sql.Connection;
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

public class RootModUserController {
	private Connection miConexion;
	UserDAO udao = new UserDAO();
	User user = new User();
	
	@FXML
	private TextField mail;
	
	@FXML
	private TextField nick;
	
	@FXML
	private PasswordField password;
	
	public void initAttributes(User u) {
		this.user = u;
		this.user.setId_user(u.getId_user());
		this.mail.setText(u.getMail());
		this.nick.setText(u.getNick());
		this.user.setPassword(u.getPassword());
		
	}
	
	@FXML
    private void saveNewUser(ActionEvent event) throws IOException {
		try {
			this.miConexion = Connect.getConnect();
			User user_aux = new User();
			String correo = mail.getText();
			String nombre = nick.getText();
			Pattern pat= Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=?])(?=\\S+$).{8,}$");
			Matcher mat= pat.matcher(password.getText());
			String contra = methods.getSHA256(password.getText());
			
			if(mat.matches()) {
				user_aux.setId_user(this.user.getId_user());
				user_aux.setMail(correo);
				user_aux.setNick(nombre);
				user_aux.setPassword(contra);
				
				boolean result = false;
				result = udao.update(user_aux);
				
				if(result!=false) {
					JOptionPane.showMessageDialog(null, "Usuario modificado con éxito");
					switchToAllUsers(event);
				} else {
		    		JOptionPane.showMessageDialog(null, "Error al modificar la usuario");
		    	}
			} else {
				JOptionPane.showMessageDialog(null, "Contraseña no válida");
			}
			
		
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	@FXML
	private void deleteUser(ActionEvent event) throws IOException  {
		boolean result = false;
		result = udao.delete(this.user);
		if(result!=false) {
			JOptionPane.showMessageDialog(null, "Usuario eliminado con éxito");
			switchToAllUsers(event);
		} else {
    		JOptionPane.showMessageDialog(null, "Error al eliminar el usuario");
    	}
	}
	
	/*
	 * Este metodo cambia a la ventana rootuser.fxml
	 */
	@FXML
    private void switchToAllUsers(ActionEvent event) throws IOException {
    	((Node) (event.getSource())).getScene().getWindow().hide();
    	Parent root = FXMLLoader.load(getClass().getResource("rootuser.fxml"));
    	Scene scene = new Scene(root,600,400);
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
}
