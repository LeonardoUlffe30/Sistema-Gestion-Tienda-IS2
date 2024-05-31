package presentacion.vista.trabajador;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import transfers.TTrabajador;

public class TrabajadorTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	private static final int COLUMS = 6;
	
	private List<TTrabajador> trabajadores;
	
	public TrabajadorTableModel() {
		trabajadores = new ArrayList<TTrabajador>();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return trabajadores.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return COLUMS;
	}
	
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Id";
		case 1:
			return "DNI";
		case 2:
			return "Nombre";
		case 3:
			return "Telefono";
		case 4:
			return "Id Tienda";
		case 5:
			return "Activo";
		default:
			return "";
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return trabajadores.get(rowIndex).getId();
		case 1:
			return trabajadores.get(rowIndex).getDni();
		case 2:
			return trabajadores.get(rowIndex).getNombre();
		case 3:
			return trabajadores.get(rowIndex).getTelefono();
		case 4:
			return trabajadores.get(rowIndex).getIdTienda();
		case 5:
			return trabajadores.get(rowIndex).isActivo() ? "Si" : "No";
		}
		return null;
	}
	
	public void addTrabajador(TTrabajador trabajador) {
		trabajadores.add(trabajador);
		fireTableDataChanged();
	}
	public void clearTable()  {
		trabajadores.clear();
		fireTableDataChanged();
	}

}
