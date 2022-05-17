package casgim.juanma.ProyectoTercerTrimestre.model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import casgim.juanma.ProyectoTercerTrimestre.model.DataObject.User;
import casgim.juanma.ProyectoTercerTrimestre.utils.Connect;
import interfaces.IDao;

public class UserDAO implements IDao<User, Integer>{
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
	public User get(Integer id) {
		User u=null;
		String sql = "SELECT id_user,mail,nick,password FROM user WHERE id_user='"+id+"'";
		try {
			PreparedStatement sentencia = miConexion.prepareStatement(sql);
			ResultSet miRs= sentencia.executeQuery();
			u=new User();
			miRs.next();
			u.setId_user(miRs.getInt("id_user"));
			u.setMail(miRs.getString("mail"));
			u.setNick(miRs.getString("nick"));
			u.setPassword(miRs.getString("password"));
			
			SubcriptionDAO sDao = new SubcriptionDAO();
			u.setMySubs(sDao.getAll());
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public Collection<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(User ob) {
		String update = "UPDATE "+ob+"FROM user";
		boolean result=false;
		try {
			PreparedStatement sentencia = miConexion.prepareStatement(update);
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
	public boolean delete(User ob) {
		String update = "DELETE "+ob+"FROM user";
		boolean result=false;
		try {
			PreparedStatement sentencia = miConexion.prepareStatement(update);
			sentencia.executeUpdate();
			result=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delById(int id) {
		// TODO Auto-generated method stub
		return false;
	}


}
