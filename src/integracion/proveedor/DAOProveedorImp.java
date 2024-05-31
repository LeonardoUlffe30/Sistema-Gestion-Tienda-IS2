package integracion.proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import integracion.db.DBConnection;
import transfers.TProveedor;

public class DAOProveedorImp implements DAOProveedor{
	
	private static final String altaProveedorSQL = "INSERT INTO Proveedor(nombre, direccion, activo) "
			+ "VALUES (?, ?, TRUE)";

	private static final String bajaProveedorSQL = "UPDATE proveedor SET activo = false WHERE id = ?";
	
	private static final String updateProveedorSQL = "UPDATE Proveedor SET nombre = ?, direccion = ?, activo = ?"
			+ " WHERE id = ?";

	private static final String readProveedorSQL = "SELECT * FROM Proveedor WHERE id = ?";
	
	private static final String readAllProveedorSQL = "SELECT * FROM Proveedor";
	
	private static final String readByNameAndAddressSQL = "SELECT * FROM Proveedor WHERE nombre = ? and direccion = ?";
	
	@Override
	public int alta(TProveedor proveedor) {
		int id = -1;
		
		try{
			Connection connection = DBConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(altaProveedorSQL, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, proveedor.getNombre());
			ps.setString(2, proveedor.getDireccion());
			
			ps.executeUpdate();
			
			// Devuelve el id del proveedor creado en la BD		
			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) id = rs.getInt(1);
						
			ps.close();
			rs.close();
		}catch (Exception e) {
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
			PreparedStatement ps = connection.prepareStatement(bajaProveedorSQL, PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setInt(1, id);
						
			//si executeUpdate ha eliminado correctamente devuelve el id del trabajador eliminado	
			if (ps.executeUpdate() == 1) { 			
				res = id;
			}
			
			ps.close();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.desconectar();
		
		return res;
	}

	@Override
	public int update(TProveedor proveedor) {
		int res = -1;
		
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(updateProveedorSQL);
			
			ps.setString(1, proveedor.getNombre());
			ps.setString(2, proveedor.getDireccion());
			ps.setBoolean(3, proveedor.isActivo());
			ps.setInt(4, proveedor.getId());
			
			//si actualiza correctamente se devolvera el id del proveedor modificado sino -1
			if (ps.executeUpdate() == 1) { 
	            res = proveedor.getId(); 
	        }
			
			ps.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();
		
		return res; 
	}

	@Override
	public TProveedor read(int id) {
		TProveedor proveedor = null;
		
		try {
			Connection connection = DBConnection.getConnection();			
			PreparedStatement ps = connection.prepareStatement(readProveedorSQL);
			
			ps.setInt(1, id);			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {		
				int idLeido = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String direccion = rs.getString("direccion");
				boolean activo = rs.getBoolean("activo");
				
				proveedor = new TProveedor(idLeido, nombre, direccion, activo);
			}
			
			ps.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();
		
		return proveedor;
	}
	
	@Override
	public int readByNameAndAddress(TProveedor proveedor) {
		int id = -1;
		
		try {
			Connection connection = DBConnection.getConnection();			
			PreparedStatement ps = connection.prepareStatement(readByNameAndAddressSQL);
			
			ps.setString(1, proveedor.getNombre());		
			ps.setString(2, proveedor.getDireccion());		
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {		
				id = rs.getInt("id");
			}
			
			ps.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();
		
		return id;
	}

	@Override
	public List<TProveedor> readAll() {
		  List<TProveedor> proveedores = new ArrayList<>();
			
			try {
				Connection connection = DBConnection.getConnection();			
				PreparedStatement ps = connection.prepareStatement(readAllProveedorSQL);
				
				ResultSet rs =  ps.executeQuery();
				
				while (rs.next()) {
					int idLeido = rs.getInt("id");
					String nombre = rs.getString("nombre");
					String direccion = rs.getString("direccion");
					boolean activo = rs.getBoolean("activo");
					
					proveedores.add(new TProveedor(idLeido, nombre, direccion, activo));
				}
				
				ps.close();
				rs.close();
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			DBConnection.desconectar();

			return proveedores;

	}
}
