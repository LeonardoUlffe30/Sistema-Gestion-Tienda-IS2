package presentacion.vista.pedido;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import transfers.TLineaPedido;

public class CarroPedidoTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private static final int COLUMNS = 3;
	
	private List<TLineaPedido> lineasPedido;
	
	public CarroPedidoTableModel() {
		lineasPedido = new ArrayList<TLineaPedido>();
	}
	
	@Override
	public int getRowCount() {
		return lineasPedido.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMNS;
	}

	@Override
	public String getColumnName(int column) {
		switch(column) {
			case 0:
				return "ID";
			case 1:
				return "Cantidad";
			case 2:
				return "Precio";
			default:
				return "";
		}
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return lineasPedido.get(rowIndex).getIdProducto();
		case 1:
			return lineasPedido.get(rowIndex).getCantidad();
		case 2:
			return lineasPedido.get(rowIndex).getPrecio();
			
		}
		return null;
	}
	
	public void addLineaPedido(TLineaPedido lineaPedido) {
		lineasPedido.add(lineaPedido);
		fireTableDataChanged();
	}
	
	public void clearTable()  {
		lineasPedido.clear();
		fireTableDataChanged();
	}

}
