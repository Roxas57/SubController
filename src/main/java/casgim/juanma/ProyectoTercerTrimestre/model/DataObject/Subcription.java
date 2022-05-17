package casgim.juanma.ProyectoTercerTrimestre.model.DataObject;

import java.time.LocalDateTime;
import java.util.Date;

public class Subcription {
	private int id_sub;
	private String service;
	private float price;
	private LocalDateTime pay_day;
	private String type;
	private int id_user;
	
	public Subcription() {
		this.id_sub = -1;
		this.service = "";
		this.price = 0;
		this.pay_day = LocalDateTime.now();
		this.type = "";
		this.id_user = -1;
	}
	
	public Subcription(int id_sub, String service, float price, LocalDateTime pay_day, String type, int id_user) {
		super();
		this.id_sub = id_sub;
		this.service = service;
		this.price = price;
		this.pay_day = pay_day;
		this.type = type;
		this.id_user = id_user;
	}

	public int getId_sub() {
		return id_sub;
	}

	public void setId_sub(int id_sub) {
		this.id_sub = id_sub;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public LocalDateTime getPay_day() {
		return pay_day;
	}

	public void setPay_day(LocalDateTime diapago) {
		this.pay_day = diapago;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	
	@Override
	public String toString() {
		return "Subcription [id_sub=" + id_sub + ", service=" + service + ", price=" + price + ", pay_day=" + pay_day
				+ ", type=" + type + "]";
	}


	
	
	
}
