package interfaces;

import java.util.Collection;

import casgim.juanma.ProyectoTercerTrimestre.model.DataObject.User;

public interface IUserDao<T, K> {
	boolean insert (T ob);
	T get (K id);
	boolean update (T ob);
	boolean delete (T ob);
	boolean delById(int id);
	User getByIdPassword(String id, String pass);
}
