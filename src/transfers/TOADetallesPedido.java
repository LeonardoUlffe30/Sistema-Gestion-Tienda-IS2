package transfers;

import java.util.List;

public class TOADetallesPedido {
	private TPedido pedido;
	private List<TLineaPedido> lineaPedidos;
	
	public TOADetallesPedido(TPedido pedido, List<TLineaPedido> lineaPedidos) {
		this.pedido = pedido;
		this.lineaPedidos = lineaPedidos;
	}
	
	public TPedido getPedido() {
		return pedido;
	}
	
	public List<TLineaPedido> getLineaPedidos() {
		return lineaPedidos;
	}
	
}
