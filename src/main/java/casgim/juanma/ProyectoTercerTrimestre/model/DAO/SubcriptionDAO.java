package casgim.juanma.ProyectoTercerTrimestre.model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import casgim.juanma.ProyectoTercerTrimestre.model.DataObject.Subcription;
import casgim.juanma.ProyectoTercerTrimestre.model.DataObject.User;
import casgim.juanma.ProyectoTercerTrimestre.utils.Connect;
import interfaces.ISubcriptionDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SubcriptionDAO implements ISubcriptionDao<Subcription, Integer>{
	private Connection miConexion;
	
	public SubcriptionDAO() {
		this.miConexion = Connect.getConnect();
	}
	
	@Override
	public boolean insert(Subcription ob) {
		
		boolean result=false;	
		String sql="INSERT INTO subscription (id_sub, service, price, pay_day, type, id_user) VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement sentencia = miConexion.prepareStatement(sql);
			sentencia.setInt(1, ob.getId_sub());
			sentencia.setString(2, ob.getService());
			sentencia.setFloat(3, ob.getPrice());
			sentencia.setObject(4, ob.getPay_day());
			sentencia.setString(5, ob.getType());
			sentencia.setInt(6, ob.getId_user());
			sentencia.executeUpdate();
			result=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Subcription get(Integer id) {
		String consulta = "SELECT * FROM subscription where codArt="+id;
		Subcription aux = new Subcription();
		try {
			Statement st = miConexion.createStatement();
			ResultSet rs = st.executeQuery(consulta);
			while(rs.next()) {
				aux.setId_sub(rs.getInt(1));
				aux.setService(rs.getString(2));
				aux.setPrice(rs.getFloat(3));
				aux.setPay_day(rs.getTimestamp(4).toLocalDateTime());
				aux.setType(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aux;
	}

	@Override
	public Collection<Subcription> getAll() {
		Collection<Subcription> result = new ArrayList<Subcription>();
		String consulta = "SELECT * FROM subscription";
		try {
			Statement st = miConexion.createStatement();
			ResultSet rs = st.executeQuery(consulta);
			while(rs.next()) {
				Subcription aux = new Subcription();
				aux.setId_sub(rs.getInt(1));
				aux.setService(rs.getString(2));
				aux.setPrice(rs.getFloat(3));
				aux.setPay_day(rs.getTimestamp(4).toLocalDateTime());
				aux.setType(rs.getString(5));
				result.add(aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Collection<Subcription> getAllSubcription(User u) {
		Collection<Subcription> listaSub = new ArrayList<Subcription>();
		String consulta = "SELECT id_sub,service,price,pay_day,type FROM subscription WHERE id_sub=?";
		try {
			PreparedStatement sentencia = miConexion.prepareStatement(consulta);
			sentencia.setInt(1, u.getId_user());
			ResultSet rs = sentencia.executeQuery(consulta);
			Subcription s=new Subcription();
			while(rs.next()) {
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
	
	public ObservableList<Subcription> getAllSub(User u) {
		ObservableList<Subcription> listaSub = FXCollections.observableArrayList();
		String consulta = "SELECT id_sub,service,price,pay_day,type FROM subscription WHERE id_user='"+u.getId_user()+"'";
		try {
			PreparedStatement sentencia = miConexion.prepareStatement(consulta);
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
	public boolean update(Subcription ob) {
		String update = "UPDATE subscription SET service=?,price=?,pay_day=?,type=? WHERE id_sub =?";
		boolean result=false;
		try {
			PreparedStatement sentencia = miConexion.prepareStatement(update);
			sentencia.setInt(5, ob.getId_sub());
			sentencia.setString(1, ob.getService());
			sentencia.setFloat(2, ob.getPrice());
			sentencia.setObject(3, ob.getPay_day());
			sentencia.setString(4, ob.getType());
			sentencia.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(Subcription ob) {
		String update = "DELETE subscription WHERE id_sub=?";
		boolean result=false;
		try {
			PreparedStatement sentencia = miConexion.prepareStatement(update);
			sentencia.setInt(1, ob.getId_sub());
			sentencia.executeUpdate();
			result=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public boolean delById(int id) {
		String update = "DELETE FROM subscription WHERE id_sub=?";
		boolean result=false;
		try {
			PreparedStatement sentencia = miConexion.prepareStatement(update);
			sentencia.setInt(1, id);
			sentencia.executeUpdate();
			result=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
