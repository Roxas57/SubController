package casgim.juanma.ProyectoTercerTrimestre.interfaces;

import java.util.Collection;
import java.util.List;

import casgim.juanma.ProyectoTercerTrimestre.model.DataObject.User;

public interface IUserDao<T, K> {
	boolean insert (T ob);
	boolean update (T ob);
	boolean delete (T ob);
	User getByIdPassword(String id, String pass);
	List<User> getAllUser();
}
