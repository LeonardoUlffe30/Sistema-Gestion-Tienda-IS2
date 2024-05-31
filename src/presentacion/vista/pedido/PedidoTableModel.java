package presentacion.vista.pedido;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import transfers.TPedido;

public class PedidoTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private static final int COLUMS = 4;
	
	private List<TPedido> pedidos;
	
	public PedidoTableModel() {
		pedidos = new ArrayList<TPedido>();
	}
	
	@Override
	public int getRowCount() {
		return pedidos.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMS;
	}
	
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Id";
		case 1:
			return "Total";
		case 2:
			return "Fecha";
		case 3:
			return "Id Trabajador";
		default:
			return "";
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return pedidos.get(rowIndex).getId();
		case 1:
			return pedidos.get(rowIndex).getTotal();
		case 2:
			return pedidos.get(rowIndex).getFecha();
		case 3:
			return pedidos.get(rowIndex).getIdTrabajador();
		}
		return null;
	}
	
	public void addPedido(TPedido pedido) {
		pedidos.add(pedido);
		fireTableDataChanged();
	}
	
	public void clearTable()  {
		pedidos.clear();
		fireTableDataChanged();
	}

}
