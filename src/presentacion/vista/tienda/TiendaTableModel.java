package presentacion.vista.tienda;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import transfers.TTienda;

public class TiendaTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private static final int COLUMS = 5;
	
	private List<TTienda> tiendas;
	
	public TiendaTableModel() {
		tiendas = new ArrayList<TTienda>();
	}
	
	@Override
	public int getRowCount() {
		return tiendas.size();
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
			return "Ciudad";
		case 2:
			return "Calle";
		case 3:
			return "Pais";
		case 4:
			return "Activo";
		default:
			return "";
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return tiendas.get(rowIndex).getId();
		case 1:
			return tiendas.get(rowIndex).getCiudad();
		case 2:
			return tiendas.get(rowIndex).getCalle();
		case 3:
			return tiendas.get(rowIndex).getPais();
		case 4:
			return tiendas.get(rowIndex).isActivo() ? "Si" : "No";	
		}
		return null;
	}

	
	public void addTienda(TTienda tienda) {
		tiendas.add(tienda);
		fireTableDataChanged();
	}
	public void clearTable(){
		tiendas.clear();
		fireTableDataChanged();
	}
}