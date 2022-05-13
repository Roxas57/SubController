package interfaces;

import java.util.Collection;

public interface IDao<T, K> {
	boolean insert (T ob);
	T get (K id);
	Collection <T> getAll();
	boolean update (T ob);
	boolean delete (T ob);
}
