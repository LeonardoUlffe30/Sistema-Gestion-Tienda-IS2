package integracion.pedido;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import integracion.db.DBConnection;
import transfers.TPedido;

public class DAOPedidoImp implements DAOPedido {

	private static final String createPedidoSQL = "INSERT INTO Pedido(id, total, fecha, id_trabajador) "
			+ "VALUES (?, ?, ?, ?)";
	
	private static final String updatePedidoSQL = "UPDATE Pedido SET total = ?, fecha = ?, id_trabajador = ? WHERE id = ?";

	private static final String readPedidoSQL = "SELECT * FROM Pedido WHERE id = ?";
	
	private static final String readAllPedidoSQL = "SELECT * FROM Pedido";
	
	private static final String readByTrabajadorSQL = "SELECT * FROM Pedido WHERE id_trabajador = ?";
	
	private static final String readByProductoSQL = "SELECT * FROM Pedido JOIN lineapedido ON "
			+ "Pedido.id = lineapedido.id_pedido WHERE id_producto = ?";
	
		
	@Override
	public int alta(TPedido pedido) {
		int id = -1;
		
		try {
			Connection connection = DBConnection.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(createPedidoSQL, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, pedido.getId());
			ps.setDouble(2, pedido.getTotal());
			ps.setDate(3, pedido.getFecha());
			ps.setInt(4, pedido.getIdTrabajador());
			
			ps.execute();
			
			// Devuelve el id del pedido creado en la BD
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
	public int update(TPedido pedido) {
		int res = -1;
		
		try {
			Connection connection = DBConnection.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(updatePedidoSQL);
			
			ps.setDouble(1, pedido.getTotal());
			ps.setDate(2, pedido.getFecha());
			ps.setInt(3, pedido.getIdTrabajador());
			ps.setInt(4, pedido.getId());
			
			ps.execute();
			
			res = pedido.getId();
			
			ps.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();
		
		return res;
	}

	@Override
	public TPedido read(int id) {
		TPedido pedidoLeido = null;
		
		try {
			Connection connection = DBConnection.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(readPedidoSQL);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				int idLeido = rs.getInt("id");
				double total = rs.getDouble("total");
				Date fecha = rs.getDate("fecha");
				int idTrabajador = rs.getInt("id_trabajador");

				pedidoLeido = new TPedido(idLeido, total, fecha, idTrabajador);
			}
			
			ps.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();
		
		return pedidoLeido;
	}

	@Override
	public List<TPedido> readAll() {
		List<TPedido> pedidosLeidos = new ArrayList<>();
		
		try {
			Connection connection = DBConnection.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(readAllPedidoSQL);
			
			ResultSet rs =  ps.executeQuery();
			
			while (rs.next()) {
				int idLeido = rs.getInt("id");
				double total = rs.getDouble("total");
				Date fecha = rs.getDate("fecha");
				int idTrabajador = rs.getInt("id_trabajador");

				pedidosLeidos.add(new TPedido(idLeido, total, fecha, idTrabajador));
			}
			
			ps.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();

		return pedidosLeidos;
	}

	@Override
	public List<TPedido> readByTrabajador(int idTrabajador) {
		List<TPedido> pedidosLeidos = new ArrayList<>();
		
		try {
			Connection connection = DBConnection.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(readByTrabajadorSQL);
			
			ps.setInt(1, idTrabajador);
			
			ResultSet rs =  ps.executeQuery();
			
			while (rs.next()) {
				int idLeido = rs.getInt("id");
				double total = rs.getDouble("total");
				Date fecha = rs.getDate("fecha");
				int idTrabajadorLeido = rs.getInt("id_trabajador");

				pedidosLeidos.add(new TPedido(idLeido, total, fecha, idTrabajadorLeido));
			}
			
			ps.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();

		return pedidosLeidos;
	}

	@Override
	public List<TPedido> readByProducto(int idProducto) {
		List<TPedido> pedidosLeidos = new ArrayList<>();
		
		try {
			Connection connection = DBConnection.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(readByProductoSQL);
			
			ps.setInt(1, idProducto);
			
			ResultSet rs =  ps.executeQuery();
			
			while (rs.next()) {
				int idLeido = rs.getInt("id");
				double total = rs.getDouble("total");
				Date fecha = rs.getDate("fecha");
				int idTrabajador = rs.getInt("id_trabajador");

				pedidosLeidos.add(new TPedido(idLeido, total, fecha, idTrabajador));
			}
			
			ps.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();

		return pedidosLeidos;
	}

}
