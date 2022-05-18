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
import casgim.juanma.ProyectoTercerTrimestre.utils.Connect;
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
    	try {
			this.miConexion = Connect.getConnect();
			String usuario = user.getText();
	    	String pass = password.getText();
	    	/*
	    	boolean result = false;
	    	result = userDao.getByIdPassword(usuario, pass);
	    	*/
	    	String sql = "SELECT id_user,mail,nick,password FROM user "
	    			+ "	WHERE nick=? AND password=?";
	    	sentencia = miConexion.prepareStatement(sql);
	    	sentencia.setString(1, usuario);
	    	sentencia.setString(2, pass);
	    	resultado = sentencia.executeQuery();
	    	
	    	if (resultado != null) {
	    		if (resultado.next()) {
	    			int id = resultado.getInt("id_user");
	    			String correo = resultado.getString("mail");
	    			String nick = resultado.getString("nick");
	    			String password = resultado.getString("password");
	    			DataService.useraux.setId_user(id);
	    			DataService.useraux.setMail(correo);
	    			DataService.useraux.setNick(nick);
	    			DataService.useraux.setPassword(password);
		    		switchToSubcription(event);
		    	} else {
		    		JOptionPane.showMessageDialog(null, "Usuario o contraseña erróneos");
		    	}
	    	} else {
	    		JOptionPane.showMessageDialog(null, "Usuario o contraseña inválidos");
	    	}
	    	
	
		} catch (SQLException ex) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, ex.getMessage());
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