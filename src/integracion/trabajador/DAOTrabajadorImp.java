package integracion.trabajador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


import java.util.ArrayList;
import integracion.db.DBConnection;
import transfers.TTrabajador;

public class DAOTrabajadorImp implements DAOTrabajador {
	 //he quitado id
	private static final String altaTrabajador = "INSERT INTO trabajador(dni, nombre, telefono, id_tienda, activo) "
			+ "VALUES (?, ?, ?, ?, TRUE)";

	private static final String bajaTrabajador = "UPDATE trabajador SET activo = false WHERE id = ?";
	
	private static final String updateTrabajador = "UPDATE trabajador SET dni = ?, nombre = ?, telefono = ?, id_tienda = ?, "
			+ "activo = ? WHERE ID = ?";

	private static final String readTrabajador = "SELECT * FROM trabajador WHERE id = ?";
	
	private static final String readAllTrabajador = "SELECT * FROM trabajador";
	
	private static final String readByTienda = "SELECT * FROM trabajador WHERE id_tienda = ?";
	
	

	@Override
	public int alta(TTrabajador trabajador) {
     
		int id = -1;
		
		try {
			
			Connection connection = DBConnection.getConnection();			
			PreparedStatement ps = connection.prepareStatement(altaTrabajador, PreparedStatement.RETURN_GENERATED_KEYS);
			
			//ps.setInt(1, trabajador.getId());
			ps.setString(1, trabajador.getDni());
			ps.setString(2, trabajador.getNombre());
			ps.setInt(3, trabajador.getTelefono());
			ps.setInt(4, trabajador.getIdTienda());
			//ps.setBoolean(5, trabajador.isActivo());
					
			ps.executeUpdate();
			
			// Devuelve el id del trabajador creado en la BD		
			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) id = rs.getInt(1);
			
			ps.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();
		
		return id;
	}

	

	@Override
	public int baja(int id) {
		int res = -1;
		
		try {
			Connection connection = DBConnection.getConnection();			
			PreparedStatement ps = connection.prepareStatement(bajaTrabajador, PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setInt(1, id);
						
			//si executeUpdate ha eliminado correctamente devuelve el id del trabajador eliminado	
			if (ps.executeUpdate() == 1) { 			
				res = id;
			}
			
			connection.close();
			ps.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public int update(TTrabajador trabajador) {
		
		int res = -1;
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(updateTrabajador);
			
			//ps.setInt(1, trabajador.getId());
			ps.setString(1, trabajador.getDni());
			ps.setString(2, trabajador.getNombre());
			ps.setInt(3, trabajador.getTelefono());
			ps.setInt(4, trabajador.getIdTienda());
			ps.setBoolean(5, trabajador.isActivo());
			ps.setInt(6, trabajador.getId());
				
			ps.execute();
	        res = trabajador.getId(); 
	        
			
			ps.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();
		
		return res; 
	}

	@Override
	public TTrabajador read(int id) {
    
		TTrabajador trabajador = null;
		
		try {
			
			Connection connection = DBConnection.getConnection();			
			PreparedStatement ps = connection.prepareStatement(readTrabajador);
			
			ps.setInt(1, id);			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				int idLeido = rs.getInt("id");
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				int telefono = rs.getInt("telefono");
				int idTienda = rs.getInt("id_tienda");
				boolean activo = rs.getBoolean("activo");
				
				trabajador = new TTrabajador(idLeido, dni, nombre, telefono, idTienda, activo);		
			}
			
			ps.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();
		
		return trabajador;
	}

	@Override
	public List<TTrabajador> readAll() {
		
		List<TTrabajador> trabajadores = new ArrayList<>();
		
		try {
			Connection connection = DBConnection.getConnection();			
			PreparedStatement ps = connection.prepareStatement(readAllTrabajador);
			
			ResultSet rs =  ps.executeQuery();
			
			while (rs.next()) {
				int idLeido = rs.getInt("id");
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				int telefono = rs.getInt("telefono");
				int idTienda = rs.getInt("id_tienda");
				boolean activo = rs.getBoolean("activo");
				
				trabajadores.add(new TTrabajador(idLeido, dni, nombre, telefono, idTienda, activo));
			}
			
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();

		return trabajadores;

	}

	@Override
	public List<TTrabajador> readByTienda(int idTienda) {
    
		List<TTrabajador> trabajadores = new ArrayList<>();
		
		try {
			
			Connection connection = DBConnection.getConnection();			
			PreparedStatement ps = connection.prepareStatement(readByTienda);
			
			ps.setInt(1, idTienda);			
			ResultSet rs = ps.executeQuery();
			
			while  (rs.next()) {								
				int idLeido = rs.getInt("id");
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				int telefono = rs.getInt("telefono");
				int idTiendaLeido = rs.getInt("id_tienda");
				boolean activo = rs.getBoolean("activo");
				
				trabajadores.add(new TTrabajador(idLeido, dni, nombre, telefono, idTiendaLeido, activo));				
			}
			
			ps.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();
		
		return trabajadores;
	}

}