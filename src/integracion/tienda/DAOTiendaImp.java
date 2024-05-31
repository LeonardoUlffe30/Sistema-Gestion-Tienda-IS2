package integracion.tienda;

import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import integracion.db.DBConnection;
import transfers.TTienda;

public class DAOTiendaImp implements DAOTienda {

	private static final String altaTiendaSQL = "INSERT INTO tienda(id, ciudad, calle, pais, activo) "
			+ "VALUES (?, ?, ?, ?, true)";

	private static final String bajaTiendaSQL = "UPDATE tienda SET activo = false WHERE id = ?";
	
	private static final String updateTiendaSQL = "UPDATE tienda SET ciudad = ?, calle = ?, pais = ?, activo = ?"
			+ " WHERE id = ?";

	private static final String readTiendaSQL = "SELECT * FROM Tienda WHERE id = ?";
	
	private static final String readAllTiendaSQL = "SELECT * FROM tienda";
	
	
	
	@Override
	public int alta(TTienda tienda) {
		int id = -1;
		
		try {
			Connection connection = DBConnection.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(altaTiendaSQL, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, tienda.getId());
			ps.setString(2, tienda.getCiudad());
			ps.setString(3, tienda.getCalle());
			ps.setString(4, tienda.getPais());
			
			ps.execute();
			
			// Devuelve el id de la tienda creada en la BD
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
			PreparedStatement ps = connection.prepareStatement(bajaTiendaSQL, PreparedStatement.RETURN_GENERATED_KEYS);

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
		DBConnection.desconectar();
		
		return res;
	}

	@Override
	public int update(TTienda tienda) {
		int res = -1;
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(updateTiendaSQL);
			
			ps.setString(1, tienda.getCiudad());
			ps.setString(2, tienda.getCalle());
			ps.setString(3, tienda.getPais());
			ps.setBoolean(4, tienda.isActivo());
			ps.setInt(5, tienda.getId());
				
			//si actualiza correctamente se devolvera el id del trabajador modificado sino -1
			if (ps.executeUpdate() == 1) { 
	            res = tienda.getId(); 
	        }
			
			ps.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();
		
		return res; 
	}

	@Override
	public TTienda read(int id) {
		TTienda tiendaLeida = null;
		
		try {
			Connection connection = DBConnection.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(readTiendaSQL);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				int idLeido = rs.getInt("id");
				String ciudad = rs.getString("ciudad");
				String calle = rs.getString("calle");
				String pais = rs.getString("pais");
				boolean activo = rs.getBoolean("activo");

				tiendaLeida = new TTienda(idLeido, ciudad, calle, pais, activo);
			}
			
			ps.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();
		
		return tiendaLeida;
	}

	

	@Override
	public List<TTienda> readAll() {
		List<TTienda> tiendas = new ArrayList<>();
		
		try {
			Connection connection = DBConnection.getConnection();			
			PreparedStatement ps = connection.prepareStatement(readAllTiendaSQL);
			
			ResultSet rs =  ps.executeQuery();
			
			while (rs.next()) {
				int idLeido = rs.getInt("id");
				String ciudad = rs.getString("ciudad");
				String calle = rs.getString("calle");
				String pais = rs.getString("pais");
				boolean activo = rs.getBoolean("activo");

				tiendas.add(new TTienda(idLeido, ciudad, calle, pais, activo));
			}
			
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();

		return tiendas;
	}

}
