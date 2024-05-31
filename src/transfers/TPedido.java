package transfers;

import java.sql.Date;

public class TPedido {
	private int id;
	private double total;
	private Date fecha;
	private int idTrabajador;
	
	public TPedido(Date fecha, int idTrabajador) {
		this.fecha = fecha;
		this.idTrabajador = idTrabajador;
		this.total = 0;
	}
	
	public TPedido(int id, Date fecha, int idTrabajador) {
		this.id = id;
		this.fecha = fecha;
		this.idTrabajador = idTrabajador;
		total = 0;
	}
	
	public TPedido(int id, double total, Date fecha, int idTrabajador) {
		this.id = id;
		this.total = total;
		this.fecha = fecha;
		this.idTrabajador = idTrabajador;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public int getIdTrabajador() {
		return idTrabajador;
	}
	
	public void setIdTrabajador(int idTrabajador) {
		this.idTrabajador = idTrabajador;
	}
}
