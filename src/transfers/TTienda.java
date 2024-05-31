package transfers;

public class TTienda {
	private int id;
	private String ciudad;
	private String calle;
	private String pais;
	private boolean activo;
	
	public TTienda(int id, String ciudad, String calle, String pais, boolean activo) {
		this.id = id;
		this.ciudad = ciudad;
		this.calle = calle;
		this.pais = pais;
		this.activo = activo;
	}
	
	public TTienda( String ciudad, String calle, String pais, boolean activo) {
		this.ciudad = ciudad;
		this.calle = calle;
		this.pais = pais;
		this.activo = activo;
	}
	public TTienda(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getCiudad(){
		return ciudad;
	}
	
	public void setCiudad(String ciudad){
		this.ciudad = ciudad;
	}
	
	public String getCalle(){
		return calle;
	}
	
	public void setCalle(String calle){
		this.calle = calle;
	}
	
	public String getPais(){
		return pais;
	}
	
	public void setPais(String pais){
		this.pais = pais;
	}

	public boolean isActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}