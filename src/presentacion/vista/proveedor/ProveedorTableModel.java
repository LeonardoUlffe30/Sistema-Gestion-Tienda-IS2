package presentacion.vista.proveedor;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import transfers.TProveedor;

public class ProveedorTableModel  extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private static final int COLUMS = 4;
	
	private List<TProveedor> proveedores;
	
	public ProveedorTableModel() {
		proveedores = new ArrayList<TProveedor>();
	}
	
	@Override
	public int getRowCount() {
		return proveedores.size();
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
			return "Nombre";
		case 2:
			return "Direccion";
		case 3:
			return "Activo";
		default:
			return "";
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return proveedores.get(rowIndex).getId();
		case 1:
			return proveedores.get(rowIndex).getNombre();
		case 2:
			return proveedores.get(rowIndex).getDireccion();
		case 3:
			return proveedores.get(rowIndex).isActivo() ? "Si" : "No";
		}
		return null;
	}
	
	public void addProveedor(TProveedor proveedor) {
		proveedores.add(proveedor);
		fireTableDataChanged();
	}
	
	public void clearTable() {
		proveedores.clear();
		fireTableDataChanged();
	}

}

