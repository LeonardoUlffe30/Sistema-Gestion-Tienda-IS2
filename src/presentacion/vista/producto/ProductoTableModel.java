package presentacion.vista.producto;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import transfers.TProducto;

public class ProductoTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private static final int COLUMS = 7;
	
	private List<TProducto> productos;
	
	public ProductoTableModel() {
		productos = new ArrayList<TProducto>();
	}
	
	@Override
	public int getRowCount() {
		return productos.size();
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
			return "Stock";
		case 2:
			return "Nombre";
		case 3:
			return "Precio";
		case 4:
			return "Id Tienda";
		case 5:
			return "Id Proveedor";
		case 6:
			return "Activo";
		default:
			return "";
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return productos.get(rowIndex).getId();
		case 1:
			return productos.get(rowIndex).getStock();
		case 2:
			return productos.get(rowIndex).getNombre();
		case 3:
			return productos.get(rowIndex).getPrecio();
		case 4:
			return productos.get(rowIndex).getIdTienda();
		case 5:
			return productos.get(rowIndex).getIdProveedor();
		case 6:
			return productos.get(rowIndex).isActivo() ? "Si" : "No";
		}
		return null;
	}
	
	public void addProducto(TProducto producto) {
		productos.add(producto);
		fireTableDataChanged();
	}
	
	public void clearTable()  {
		productos.clear();
		fireTableDataChanged();
	}

}
