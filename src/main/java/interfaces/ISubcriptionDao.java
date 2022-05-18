package interfaces;

import java.util.Collection;

import casgim.juanma.ProyectoTercerTrimestre.model.DataObject.User;

public interface ISubcriptionDao<T, K> {
	boolean insert (T ob);
	T get (K id);
	Collection <T> getAll();
	boolean update (T ob);
	boolean delete (T ob);
	boolean delById(int id);
}
