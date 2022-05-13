package casgim.juanma.ProyectoTercerTrimestre.utils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "conexion")
@XmlAccessorType(XmlAccessType.FIELD)
public class Connect {
	private static Connection con;
	private String file = "conexion.xml";
	private static Connect _newInstance;
	
	private Connect() {
		//Cargamos los datos de la conexion del XML
		DatosConexion dc=load();
		//Establecemos la conexion
		try {
			con=DriverManager.getConnection(dc.getServer()+"/"+dc.getDatabase(),dc.getUsername(),dc.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			con=null;
		}
	}
	
	public static Connection getConnect() {
		if(_newInstance == null) {
			_newInstance = new Connect();
		}
		return con;
	}
	
	public DatosConexion load() {
		DatosConexion datosCon=new DatosConexion();
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(DatosConexion.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			DatosConexion newR = (DatosConexion) jaxbUnmarshaller.unmarshal(new File(file));
			datosCon=newR;
		} catch (JAXBException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return datosCon;
	}
	
}
