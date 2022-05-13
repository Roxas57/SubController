package casgim.juanma.ProyectoTercerTrimestre.model.DataObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User {
	private int id_user;
	private String mail;
	private String nick;
	private String password;
	private Collection<Subcription> mySubs;
	
	public User() {
		this.id_user = 0;
		this.mail = "";
		this.nick = "";
		this.password = "";
		this.mySubs = new ArrayList<>();
	}
	
	public User(int id_user, String mail, String nick, String password, List<Subcription> mySubs) {
		super();
		this.id_user = id_user;
		this.mail = mail;
		this.nick = nick;
		this.password = password;
		this.mySubs = mySubs;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Subcription> getMySubs() {
		return mySubs;
	}

	public void setMySubs(Collection<Subcription> collection) {
		this.mySubs = collection;
	}

	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", mail=" + mail + ", nick=" + nick + ", password=" + password + ", mySubs="
				+ mySubs + "]";
	}
	
	
	
	
}
