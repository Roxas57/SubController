package casgim.juanma.ProyectoTercerTrimestre.model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;

import casgim.juanma.ProyectoTercerTrimestre.DataService;
import casgim.juanma.ProyectoTercerTrimestre.interfaces.IUserDao;
import casgim.juanma.ProyectoTercerTrimestre.model.DataObject.Subcription;
import casgim.juanma.ProyectoTercerTrimestre.model.DataObject.User;
import casgim.juanma.ProyectoTercerTrimestre.utils.Connect;
import javafx.collections.FXCollections;

public class UserDAO implements IUserDao<User, Integer>{
	private Connection miConexion;

	
	public UserDAO() {
		this.miConexion = Connect.getConnect();

	}

	@Override
	public boolean insert(User ob) {
		boolean result=false;
		String sql="INSERT INTO user (id_user,mail,nick,password) VALUES (?,?,?,?)";
		try {
			PreparedStatement sentencia = miConexion.prepareStatement(sql);
			sentencia.setInt(1, ob.getId_user());
			sentencia.setString(2, ob.getMail());
			sentencia.setString(3, ob.getNick());
			sentencia.setString(4, ob.getPassword());
			sentencia.executeUpdate();
			result=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<User> getAllUser() {
		List<User> listausuarios = FXCollections.observableArrayList();
		String sql = "SELECT id_user,mail,nick,password FROM user";
		try {
			PreparedStatement sentencia = miConexion.prepareStatement(sql);
			ResultSet rs= sentencia.executeQuery();
			while(rs.next()) {
				User u=new User();
				u.setId_user(rs.getInt("id_user"));
				u.setMail(rs.getString("mail"));
				u.setNick(rs.getString("nick"));
				u.setPassword(rs.getString("password"));
				
				listausuarios.add(u);
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return listausuarios;
	}
	
	@Override
	public User getByIdPassword(String id_user, String pass) {
		User u=new User();
		
		String sql = "SELECT id_user,mail,nick,password FROM user "
    			+ "	WHERE nick=? AND password=?";
		try {
			PreparedStatement sentencia = miConexion.prepareStatement(sql);
			sentencia.setString(1, id_user);
	    	sentencia.setString(2, pass);
			ResultSet resultado= sentencia.executeQuery();
			if (resultado != null) {
	    		if (resultado.next()) {
	    			u.setId_user(resultado.getInt("id_user"));
	    			u.setMail(resultado.getString("mail"));
	    			u.setNick(resultado.getString("nick"));
	    			u.setPassword(resultado.getString("password"));
	    			DataService.useraux.setId_user(u.getId_user());
	    			DataService.useraux.setMail(u.getMail());
	    			DataService.useraux.setNick(u.getNick());
	    			DataService.useraux.setPassword(u.getPassword());
	    		} else {
		    		JOptionPane.showMessageDialog(null, "Usuario o contraseña erróneos");
		    	}
			} else {
	    		JOptionPane.showMessageDialog(null, "Usuario o contraseña inválidos");
	    	}
			u.setMySubs(getAllSub(u));
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	public List<Subcription> getAllSub(User u) {
		List<Subcription> listaSub = FXCollections.observableArrayList();
		String consulta = "SELECT id_sub,service,price,pay_day,type FROM subscription WHERE id_user=?";
		try {
			PreparedStatement sentencia = miConexion.prepareStatement(consulta);
			sentencia.setInt(1, u.getId_user());
			ResultSet rs = sentencia.executeQuery();
			while(rs.next()) {
				Subcription s=new Subcription();
				s.setId_sub(rs.getInt("id_sub"));
				s.setService(rs.getString("service"));
				s.setPrice(rs.getFloat("price"));
				s.setPay_day(rs.getTimestamp("pay_day").toLocalDateTime());
				s.setType(rs.getString("type"));
				
				listaSub.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaSub;
	}

	@Override
	public boolean update(User ob) {
		String update = "UPDATE user SET mail=?,nick=?,password=? WHERE id_user=?";
		boolean result=false;
		try {
			PreparedStatement sentencia = miConexion.prepareStatement(update);
			sentencia.setInt(4, ob.getId_user());
			sentencia.setString(1, ob.getMail());
			sentencia.setString(2, ob.getNick());
			sentencia.setString(3, ob.getPassword());
			sentencia.executeUpdate();
			result=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(User ob) {
		String update = "DELETE FROM user WHERE id_user=?";
		boolean result=false;
		try {
			PreparedStatement sentencia = miConexion.prepareStatement(update);
			sentencia.setInt(1, ob.getId_user());
			sentencia.executeUpdate();
			result=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}



}
