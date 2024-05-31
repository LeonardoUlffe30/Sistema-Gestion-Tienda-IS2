package integracion.lineapedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import integracion.db.DBConnection;
import transfers.TLineaPedido;

public class DAOLineaPedidoImp implements DAOLineaPedido {
	
	private static final String addSQL = "INSERT INTO lineapedido (id_pedido, id_producto, cantidad, precio) "
			+ "VALUES (?, ?, ?, ?)";
	
	private static final String updateSQL = "UPDATE lineapedido SET cantidad = ?, precio = ? WHERE id_pedido = ? AND id_producto = ?";
	
	private static final String deleteSQL = "DELETE FROM lineapedido WHERE id_pedido = ? AND id_producto = ?";
	
	private static final String readSQL = "SELECT * FROM lineapedido WHERE id_pedido = ? AND id_producto = ?";
	
	private static final String readByPedidoSQL = "SELECT * FROM lineapedido WHERE id_pedido = ?";

	@Override
	public int addProductoPedido(TLineaPedido lineaPedido) {
		int res = -1;
		
		try {
			Connection connection = DBConnection.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(addSQL);
			
			ps.setInt(1, lineaPedido.getIdPedido());
			ps.setInt(2, lineaPedido.getIdProducto());
			ps.setInt(3, lineaPedido.getCantidad());
			ps.setDouble(4, lineaPedido.getPrecio());

			ps.execute();
			
			res = 1;
			ps.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();
		
		return res;
	}
	
	@Override
	public int deleteProductoPedido(TLineaPedido lineaPedido) {
		int res = -1;
		
		try {
			Connection connection = DBConnection.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(deleteSQL);
			
			ps.setInt(1, lineaPedido.getIdPedido());
			ps.setInt(2, lineaPedido.getIdProducto());

			ps.execute();
			
			res = 1;
			ps.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();
		
		return res;
	}
	
	@Override
	public int update(TLineaPedido lineaPedido) {
		int res = -1;
		
		try {
			Connection connection = DBConnection.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(updateSQL);
			
			ps.setInt(1, lineaPedido.getCantidad());
			ps.setDouble(2, lineaPedido.getPrecio());
			ps.setInt(3, lineaPedido.getIdPedido());
			ps.setInt(4, lineaPedido.getIdProducto());

			ps.execute();
			
			res = 1;
			ps.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();
		
		return res;
	}

	@Override
	public TLineaPedido read(int idPedido, int idProducto) {
		TLineaPedido lineaPedidoLeido = null;
		
		try {
			Connection connection = DBConnection.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(readSQL);
			
			ps.setInt(1, idPedido);
			ps.setInt(2,  idProducto);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				int idPedidoLeido = rs.getInt("id_pedido");
				int idProductoLeido = rs.getInt("id_producto");
				int cantidad = rs.getInt("cantidad");
				double precio = rs.getDouble("precio");

				lineaPedidoLeido = new TLineaPedido(idPedidoLeido, idProductoLeido, cantidad, precio);
			}
			
			ps.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();
		
		return lineaPedidoLeido;
	}

	@Override
	public List<TLineaPedido> readByPedido(int idPedido) {
		List<TLineaPedido> lineaPedidoLeidos = new ArrayList<>();
		
		try {
			Connection connection = DBConnection.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(readByPedidoSQL);
			
			ps.setInt(1, idPedido);
			
			ResultSet rs =  ps.executeQuery();
			
			while (rs.next()) {
				int idPedidoLeido = rs.getInt("id_pedido");
				int idProductoLeido = rs.getInt("id_producto");
				int cantidad = rs.getInt("cantidad");
				double precio = rs.getDouble("precio");
				
				lineaPedidoLeidos.add(new TLineaPedido(idPedidoLeido, idProductoLeido, cantidad, precio));
			}
			
			ps.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DBConnection.desconectar();

		return lineaPedidoLeidos;
	}
	
}