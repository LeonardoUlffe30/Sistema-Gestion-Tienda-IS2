package integracion.producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import integracion.db.DBConnection;
import transfers.TProducto;

public class DAOProductoImp implements DAOProducto {
	
	private static final String altaProductoSQL = "INSERT INTO Producto(stock, nombre, precio, id_tienda, id_proveedor, activo) "
			+ "VALUES (?, ?, ?, ?, ?, TRUE)";

	private static final String bajaProductoSQL = "UPDATE Producto SET activo = FALSE WHERE id = ?";
	
	private static final String updateProductoSQL = "UPDATE Producto SET stock = ?, nombre = ?, precio = ?, id_tienda = ?, id_proveedor = ?, activo = ?"
			+ " WHERE ID = ?";

	private static final String readProductoSQL = "SELECT * FROM Producto WHERE id = ?";
	
	private static final String readAllProductoSQL = "SELECT * FROM Producto";
	
	private static final String readByTiendaSQL = "SELECT * FROM Producto WHERE id_tienda = ?";
	
	private static final String readByProveedorSQL = "SELECT * FROM Producto WHERE id_proveedor = ?";
	
	private static final String readByPedidoSQL = "SELECT * FROM Producto JOIN lineapedido ON Producto.id = lineapedido.id_producto"
			+ "WHERE lineapedido.id_pedido = ?";

	
	@Override
	public int alta(TProducto producto) {
		int id = -1;
		
		try {
			Connection connection = DBConnection.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(altaProductoSQL, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, producto.getStock());
			ps.setString(2, producto.getNombre());
			ps.setDouble(3, producto.getPrecio());
			ps.setInt(4, producto.getIdTienda());
			ps.setInt(5, producto.getIdProveedor());
			
			ps.execute();
			
			// Devuelve el id del producto creado en la BD
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
			
			PreparedStatement ps = connection.prepareStatement(bajaProductoSQL);
			
			ps.setInt(1, id);
			
			ps.execute();
			
			res = id;
			
			ps.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();
		
		return res;
	}

	@Override
	public int update(TProducto producto) {
		int res = -1;
		try {
			Connection connection = DBConnection.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(updateProductoSQL);
			
			ps.setInt(1, producto.getStock());
			ps.setString(2, producto.getNombre());
			ps.setDouble(3, producto.getPrecio());
			ps.setInt(4, producto.getIdTienda());
			ps.setInt(5, producto.getIdProveedor());
			ps.setBoolean(6, producto.isActivo());
			ps.setInt(7, producto.getId());
			
			ps.execute();
			
			res = producto.getId();
			
			ps.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();
		
		return res;
	}

	@Override
	public TProducto read(int id) {
		TProducto productoLeido = null;
		
		try {
			Connection connection = DBConnection.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(readProductoSQL);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				int idLeido = rs.getInt("id");
				int stock = rs.getInt("stock");
				String nombre = rs.getString("nombre");
				Double precio = rs.getDouble("precio");
				int idTienda = rs.getInt("id_tienda");
				int idProveedor = rs.getInt("id_proveedor");
				boolean activo = rs.getBoolean("activo");

				productoLeido = new TProducto(idLeido, stock, nombre, precio, idTienda, idProveedor, activo);
			}
			
			ps.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();
		
		return productoLeido;
	}

	@Override
	public List<TProducto> readAll() {
		List<TProducto> productosLeidos = new ArrayList<>();
		
		try {
			Connection connection = DBConnection.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(readAllProductoSQL);
			
			ResultSet rs =  ps.executeQuery();
			
			while (rs.next()) {
				int idLeido = rs.getInt("id");
				int stock = rs.getInt("stock");
				String nombre = rs.getString("nombre");
				Double precio = rs.getDouble("precio");
				int idTienda = rs.getInt("id_tienda");
				int idProveedor = rs.getInt("id_proveedor");
				boolean activo = rs.getBoolean("activo");

				productosLeidos.add(new TProducto(idLeido, stock, nombre, precio, idTienda, idProveedor, activo));
			}
			
			ps.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();

		return productosLeidos;
	}

	@Override
	public List<TProducto> readByTienda(int idTienda) {
		List<TProducto> productosLeidos = new ArrayList<>();
		
		try {
			Connection connection = DBConnection.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(readByTiendaSQL);
			
			ps.setInt(1, idTienda);
			
			ResultSet rs =  ps.executeQuery();
			
			while (rs.next()) {
				int idLeido = rs.getInt("id");
				int stock = rs.getInt("stock");
				String nombre = rs.getString("nombre");
				Double precio = rs.getDouble("precio");
				int idTiendaLeido = rs.getInt("id_tienda");
				int idProveedor = rs.getInt("id_proveedor");
				boolean activo = rs.getBoolean("activo");

				productosLeidos.add(new TProducto(idLeido, stock, nombre, precio, idTiendaLeido, idProveedor, activo));	
			}
			
			ps.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();

		return productosLeidos;
	}

	@Override
	public List<TProducto> readByProveedor(int idProveedor) {
		List<TProducto> productosLeidos = new ArrayList<>();
		
		try {
			Connection connection = DBConnection.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(readByProveedorSQL);
			
			ps.setInt(1, idProveedor);
			
			ResultSet rs =  ps.executeQuery();
			
			while (rs.next()) {
				int idLeido = rs.getInt("id");
				int stock = rs.getInt("stock");
				String nombre = rs.getString("nombre");
				Double precio = rs.getDouble("precio");
				int idTienda = rs.getInt("id_tienda");
				int idProveedorLeido = rs.getInt("id_proveedor");
				boolean activo = rs.getBoolean("activo");

				productosLeidos.add(new TProducto(idLeido, stock, nombre, precio, idTienda, idProveedorLeido, activo));
			}
			
			ps.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();

		return productosLeidos;
	}

	@Override
	public List<TProducto> readByPedido(int idPedido) {
		List<TProducto> productosLeidos = new ArrayList<>();
		
		try {
			Connection connection = DBConnection.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(readByPedidoSQL);
			
			ps.setInt(1, idPedido);
			
			ResultSet rs =  ps.executeQuery();
			
			while (rs.next()) {
				int idLeido = rs.getInt("id");
				int stock = rs.getInt("stock");
				String nombre = rs.getString("nombre");
				Double precio = rs.getDouble("precio");
				int idTienda = rs.getInt("id_tienda");
				int idProveedorLeido = rs.getInt("id_proveedor");
				boolean activo = rs.getBoolean("activo");

				productosLeidos.add(new TProducto(idLeido, stock, nombre, precio, idTienda, idProveedorLeido, activo));
			}
			
			ps.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();

		return productosLeidos;
	}

}
